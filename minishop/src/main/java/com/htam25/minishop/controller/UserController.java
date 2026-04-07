package com.htam25.minishop.controller;

import com.htam25.minishop.dto.request.UpdateProfileRequest;
import com.htam25.minishop.dto.response.UserResponse;
import com.htam25.minishop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/me")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping
    public UserResponse getMe() {
        return userService.getMe();
    }

    @PutMapping
    public UserResponse updateProfile(@Valid @RequestBody UpdateProfileRequest request) {
        return userService.updateProfile(request);
    }
}
