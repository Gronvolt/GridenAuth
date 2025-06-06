package com.neomodeon.gridenauth.service.impl;

import com.neomodeon.gridenauth.dto.UserRegisterRequest;
import com.neomodeon.gridenauth.dto.UserRegisterResponse;
import com.neomodeon.gridenauth.entity.Role;
import com.neomodeon.gridenauth.entity.User;
import com.neomodeon.gridenauth.mapper.UserMapper;
import com.neomodeon.gridenauth.repository.UserRepository;
import com.neomodeon.gridenauth.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserMapper userMapper;

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
}
