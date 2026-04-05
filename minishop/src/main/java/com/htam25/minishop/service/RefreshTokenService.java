package com.htam25.minishop.service;

import com.htam25.minishop.entity.RefreshToken;
import com.htam25.minishop.entity.User;

public interface RefreshTokenService {

    RefreshToken create(User user);

    RefreshToken verify(String tokenStr);

    void revoke(String tokenStr);
}
