package com.htam25.minishop.service.impl;

import com.htam25.minishop.entity.RefreshToken;
import com.htam25.minishop.entity.User;
import com.htam25.minishop.repository.RefreshTokenRepository;
import com.htam25.minishop.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository repository;

    @Override
    public RefreshToken create(User user) {
        RefreshToken token = new RefreshToken();

        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(LocalDateTime.now().plusDays(7));

        return repository.save(token);
    }

    @Override
    public RefreshToken verify(String tokenStr) {
        RefreshToken token = repository.findByToken(tokenStr)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (token.isRevoked() || token.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expired or revoked");
        }

        return token;
    }

    @Override
    public void revoke(String tokenStr) {
        RefreshToken token = repository.findByToken(tokenStr)
                .orElseThrow(() -> new RuntimeException("Not found"));

        token.setRevoked(true);
        repository.save(token);
    }
}
