package com.htam25.minishop.service.impl;

import com.htam25.minishop.dto.request.UpdateProfileRequest;
import com.htam25.minishop.dto.response.UserResponse;
import com.htam25.minishop.entity.User;
import com.htam25.minishop.repository.UserRepository;
import com.htam25.minishop.security.user.CurrentUserService;
import com.htam25.minishop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final CurrentUserService currentUserService;
    private final UserRepository userRepository;

    @Override
    public UserResponse getMe() {
        User user = currentUserService.getCurrentUser();

        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .build();
    }

    @Override
    public UserResponse updateProfile(UpdateProfileRequest request) {
        User user = currentUserService.getCurrentUser();

        if(request.getUsername() != null) {
            user.setUsername(request.getUsername());
        }

        userRepository.save(user);

        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .build();
    }
}
