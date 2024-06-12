package com.dylabo.dydev.domain.user.service;

import com.dylabo.dydev.domain.user.entity.User;
import com.dylabo.dydev.domain.user.service.dto.UserResponseDto;

import java.util.Optional;

public interface UserService {

    Optional<User> getUserEntityByUserId(String userId);

    UserResponseDto getUserById(Long id);

    UserResponseDto getUserByUserId(String userId);

}
