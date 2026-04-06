package com.htam25.minishop.service.impl;

import com.htam25.minishop.entity.CartItem;
import com.htam25.minishop.entity.Product;
import com.htam25.minishop.entity.User;
import com.htam25.minishop.repository.CartItemRepository;
import com.htam25.minishop.repository.ProductRepository;
import com.htam25.minishop.repository.UserRepository;
import com.htam25.minishop.security.user.CurrentUserService;
import com.htam25.minishop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final CurrentUserService currentUserService;

    @Override
    public void addToCart(Long productId, int quantity) {

        User user = currentUserService.getCurrentUser();
        Long userId = currentUserService.getUserId();

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem cartItem = cartItemRepository
                .findByUser_IdAndProduct_Id(userId, productId)
                .orElseGet(() -> null);

        int cartQuantity = (cartItem != null) ? cartItem.getQuantity() : 0;
        int newQuantity = quantity + cartQuantity;

        if (newQuantity > product.getStock()) {
            throw new RuntimeException("Not enough stock");
        }

        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setProduct(product);
        }

        cartItem.setQuantity(newQuantity);
        cartItemRepository.save(cartItem);
    }

    @Override
    public void updateCartItem(Long cartItemId, int quantity) {

        User user = currentUserService.getCurrentUser();
        System.out.println(cartItemId);
        System.out.println(user.getId());
        CartItem cartItem = cartItemRepository.findByIdAndUser_Id(cartItemId, user.getId())
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        Product product = cartItem.getProduct();
        if (quantity > product.getStock()) {
            throw new RuntimeException("Not enough stock");
        }

        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);

    }

    @Override
    public void removeFromCart(Long productId){
        Long userId = currentUserService.getUserId();
        cartItemRepository.findByUser_IdAndProduct_Id(userId, productId)
                .ifPresent(cartItemRepository::delete);
    }

    @Override
    public List<CartItem> getCart() {
        Long userId = currentUserService.getUserId();
        return cartItemRepository.findByUser_Id(userId);
    }

    @Override
    public void clearCart() {
        Long userId = currentUserService.getUserId();
        cartItemRepository.deleteByUser_Id(userId);
    }
}
