package com.htam25.minishop.service;

import com.htam25.minishop.dto.request.UpdateProfileRequest;
import com.htam25.minishop.dto.response.UserResponse;


public interface UserService {

    UserResponse getMe();

    UserResponse updateProfile(UpdateProfileRequest request);

}
