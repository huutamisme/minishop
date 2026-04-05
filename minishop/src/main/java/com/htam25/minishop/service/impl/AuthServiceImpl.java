package com.htam25.minishop.service.impl;

import com.htam25.minishop.dto.request.LoginRequest;
import com.htam25.minishop.dto.request.RegisterRequest;
import com.htam25.minishop.dto.response.AuthResponse;
import com.htam25.minishop.entity.RefreshToken;
import com.htam25.minishop.entity.Role;
import com.htam25.minishop.entity.User;
import com.htam25.minishop.repository.RoleRepository;
import com.htam25.minishop.repository.UserRepository;
import com.htam25.minishop.security.jwt.JwtUtil;
import com.htam25.minishop.security.util.CookieUtil;
import com.htam25.minishop.service.AuthService;
import com.htam25.minishop.service.RefreshTokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse login(LoginRequest request, HttpServletResponse response) {

        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail()).get();

        String accessToken = jwtUtil.generateToken(user.getEmail());

        RefreshToken refreshToken = refreshTokenService.create(user);

        CookieUtil.addRefreshToken(response, refreshToken.getToken());

        return new AuthResponse(accessToken, "Bearer");
    }

    @Override
    public AuthResponse refresh(HttpServletRequest request) {
        String tokenStr = CookieUtil.getRefreshToken(request);

        RefreshToken token = refreshTokenService.verify(tokenStr);

        String newAccessToken = jwtUtil.generateToken(
                token.getUser().getEmail()
        );

        return new AuthResponse(newAccessToken, "Bearer");
    }

    @Override
    public void logout(HttpServletResponse response, HttpServletRequest request) {
        String tokenStr = CookieUtil.getRefreshToken(request);

        if(tokenStr != null) {
            refreshTokenService.revoke(tokenStr);
        }

        CookieUtil.clear(response);
    }

    @Override
    public AuthResponse register(RegisterRequest request, HttpServletResponse response) {
        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        Role role = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(role);

        userRepository.save(user);

        String accessToken = jwtUtil.generateToken(user.getEmail());

        RefreshToken refreshToken = refreshTokenService.create(user);

        CookieUtil.addRefreshToken(response, refreshToken.getToken());

        return new AuthResponse(accessToken, "Bearer");
    }
}
