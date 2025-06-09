package com.neomodeon.gridenauth.service.impl;

import com.neomodeon.gridenauth.dto.AuthRequest;
import com.neomodeon.gridenauth.dto.AuthResponse;
import com.neomodeon.gridenauth.dto.UserRegisterRequest;
import com.neomodeon.gridenauth.dto.UserRegisterResponse;
import com.neomodeon.gridenauth.entity.Role;
import com.neomodeon.gridenauth.entity.User;
import com.neomodeon.gridenauth.mapper.UserMapper;
import com.neomodeon.gridenauth.repository.UserRepository;
import com.neomodeon.gridenauth.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtServiceImpl jwtServiceImpl;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public UserRegisterResponse save(UserRegisterRequest userDto) {
        User user = new User(
                userDto.username(),
                userDto.email(),
                bCryptPasswordEncoder.encode(userDto.password()),
                Role.USER
        );

        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public AuthResponse login(AuthRequest authDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authDto.username(), authDto.password())
        );
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String AccessToken = jwtServiceImpl.generateToken(userDetails);
        String RefreshToken = jwtServiceImpl.generateRefreshToken(userDetails);
        return new AuthResponse(AccessToken, RefreshToken);
    }

    @Override
    public AuthResponse refreshToken(Map<String, String> request) {
        String refreshToken = request.get("refreshToken");

        String username = jwtServiceImpl.extractUsername(refreshToken);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        if (!jwtServiceImpl.validateToken(refreshToken, userDetails)) {
            throw new IllegalArgumentException("Invalid refresh token");
        }

        String newAccessToken = jwtServiceImpl.generateToken(userDetails);
        String newRefreshToken = jwtServiceImpl.generateRefreshToken(userDetails);
        return new AuthResponse(newAccessToken, newRefreshToken);
    }


}
