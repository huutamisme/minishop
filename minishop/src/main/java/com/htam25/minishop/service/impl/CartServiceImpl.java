package com.htam25.minishop.service.impl;

import com.htam25.minishop.entity.CartItem;
import com.htam25.minishop.entity.Product;
import com.htam25.minishop.entity.User;
import com.htam25.minishop.repository.CartItemRepository;
import com.htam25.minishop.repository.ProductRepository;
import com.htam25.minishop.repository.UserRepository;
import com.htam25.minishop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public void addToCart(Long userId, Long productId, int quantity) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem cartItem = cartItemRepository
                .findByUser_IdAndProduct_Id(userId, productId)
                .orElseGet(() -> {
                    CartItem newItem = new CartItem();
                    newItem.setUser(user);
                    newItem.setProduct(product);
                    newItem.setQuantity(0);
                    return newItem;
                });
        cartItem.setQuantity(cartItem.getQuantity() + quantity);
        cartItemRepository.save(cartItem);
    }

    @Override
    public void removeFromCart(Long userId, Long productId){
        cartItemRepository.findByUser_IdAndProduct_Id(userId, productId)
                .ifPresent(cartItemRepository::delete);
    }

    @Override
    public List<CartItem> getCart(Long userId) {
        return cartItemRepository.findByUser_Id(userId);
    }

    @Override
    public void clearCart(Long userId) {
        cartItemRepository.deleteByUser_Id(userId);
    }


}
