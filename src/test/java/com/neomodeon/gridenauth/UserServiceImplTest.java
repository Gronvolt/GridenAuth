package com.neomodeon.gridenauth;

import com.neomodeon.gridenauth.dto.AuthRequest;
import com.neomodeon.gridenauth.dto.AuthResponse;
import com.neomodeon.gridenauth.entity.Role;
import com.neomodeon.gridenauth.entity.User;
import com.neomodeon.gridenauth.mapper.UserMapper;
import com.neomodeon.gridenauth.repository.UserRepository;
import com.neomodeon.gridenauth.service.impl.CustomUserDetailsService;
import com.neomodeon.gridenauth.service.impl.JwtServiceImpl;
import com.neomodeon.gridenauth.service.impl.UserDetailsImpl;
import com.neomodeon.gridenauth.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceImplTest {
    private AuthenticationManager authenticationManager;
    private JwtServiceImpl jwtServiceImpl;
    private UserServiceImpl userServiceImpl;

    @BeforeEach
    void setUp() {
        UserRepository userRepository = mock(UserRepository.class);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        UserMapper userMapper = mock(UserMapper.class);
        authenticationManager = mock(AuthenticationManager.class);
        jwtServiceImpl = mock(JwtServiceImpl.class);
        userServiceImpl = mock(UserServiceImpl.class);
        CustomUserDetailsService customUserDetailsService = mock(CustomUserDetailsService.class);

        userServiceImpl = new UserServiceImpl(
                userRepository,
                bCryptPasswordEncoder,
                userMapper,
                authenticationManager,
                jwtServiceImpl,
                customUserDetailsService);
    }

    @Test
    void login() {
        AuthRequest authRequest = new AuthRequest("testUsername", "testPassword");

        UserDetailsImpl userDetails = new UserDetailsImpl(new User("testUsername", "testEmail@example.com" ,"testPassword", Role.USER));
        Authentication authResult = mock(Authentication.class);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authResult);

        when(authResult.getPrincipal()).thenReturn(userDetails);

        when(jwtServiceImpl.generateToken(any())).thenReturn("ACCESS_TOKEN");
        when(jwtServiceImpl.generateRefreshToken(any())).thenReturn("REFRESH_TOKEN");

        AuthResponse result = userServiceImpl.login(authRequest);

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtServiceImpl).generateToken(userDetails);
        verify(jwtServiceImpl).generateRefreshToken(userDetails);

        assertEquals("ACCESS_TOKEN", result.accessToken());
        assertEquals("REFRESH_TOKEN", result.refreshToken());
    }
}
