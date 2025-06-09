package com.neomodeon.gridenauth.service;

import com.neomodeon.gridenauth.dto.AuthRequest;
import com.neomodeon.gridenauth.dto.AuthResponse;
import com.neomodeon.gridenauth.dto.UserRegisterRequest;
import com.neomodeon.gridenauth.dto.UserRegisterResponse;

import java.util.Map;

public interface UserService {
    UserRegisterResponse save(UserRegisterRequest userDto);
    AuthResponse login (AuthRequest authDto);
    AuthResponse refreshToken (Map<String, String> request);
}
