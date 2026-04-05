package com.htam25.minishop.controller;

import com.htam25.minishop.dto.request.LoginRequest;
import com.htam25.minishop.dto.request.RegisterRequest;
import com.htam25.minishop.dto.response.AuthResponse;
import com.htam25.minishop.entity.RefreshToken;
import com.htam25.minishop.entity.User;
import com.htam25.minishop.repository.UserRepository;
import com.htam25.minishop.security.jwt.JwtUtil;
import com.htam25.minishop.security.util.CookieUtil;
import com.htam25.minishop.service.RefreshTokenService;
import com.htam25.minishop.service.impl.AuthServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request, HttpServletResponse response) {
        return authService.login(request, response);
    }

    @PostMapping("/refresh")
    public AuthResponse refresh(HttpServletRequest request) {
        return authService.refresh(request);
    }

    @PostMapping("/logout")
    public void logout(HttpServletResponse response, HttpServletRequest request) {
        authService.logout(response, request);
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request, HttpServletResponse response) {
        return authService.register(request, response);
    }
}
