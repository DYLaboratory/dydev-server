package com.dylabo.dydev.domain.user.service.impl;

import com.dylabo.core.common.exception.ApiException;
import com.dylabo.core.common.exception.message.ErrorMessage;
import com.dylabo.dydev.domain.user.entity.User;
import com.dylabo.dydev.domain.user.repository.UserRepository;
import com.dylabo.dydev.domain.user.service.UserService;
import com.dylabo.dydev.domain.user.service.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Override
    public Optional<User> getUserEntityByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorMessage.USER_NOT_FOUND));

        return modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    public UserResponseDto getUserByUserId(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new ApiException(ErrorMessage.USER_NOT_FOUND));

        return modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    public User loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<User> userOp = getUserEntityByUserId(userId);

        return userOp.orElseThrow(() -> new UsernameNotFoundException(userId));
    }

}
