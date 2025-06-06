package com.neomodeon.gridenauth.service;

import com.neomodeon.gridenauth.dto.UserRegisterRequest;
import com.neomodeon.gridenauth.dto.UserRegisterResponse;

public interface UserService {
    UserRegisterResponse save(UserRegisterRequest userDto);
}
