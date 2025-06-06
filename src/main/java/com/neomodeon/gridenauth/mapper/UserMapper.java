package com.neomodeon.gridenauth.mapper;

import com.neomodeon.gridenauth.dto.UserRegisterResponse;
import com.neomodeon.gridenauth.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserRegisterResponse toDto(User user);
}
