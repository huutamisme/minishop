package com.htam25.minishop.service;

import com.htam25.minishop.dto.request.LoginRequest;
import com.htam25.minishop.dto.response.AuthResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

    AuthResponse login(LoginRequest request, HttpServletResponse response);

    AuthResponse refresh(HttpServletRequest request);

    void logout(HttpServletResponse response, HttpServletRequest request);
}
