package com.htam25.minishop.service;

import com.htam25.minishop.entity.CartItem;

import java.util.List;

public interface CartService {

    void addToCart(Long productId, int quantity);

    void updateCartItem(Long cartItemId, int quantity);

    void removeFromCart(Long productId);

    List<CartItem> getCart();

    void clearCart();
}
