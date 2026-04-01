package com.htam25.minishop.service;

import com.htam25.minishop.entity.CartItem;

import java.util.List;

public interface CartService {

    void addToCart(Long userId, Long productId, int quantity);

    void removeFromCart(Long userId, Long productId);

    List<CartItem> getCart(Long userId);

    void clearCart(Long userId);
}
