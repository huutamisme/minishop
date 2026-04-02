package com.htam25.minishop.controller;

import com.htam25.minishop.dto.request.AddToCartRequest;
import com.htam25.minishop.dto.response.CartItemResponse;
import com.htam25.minishop.mapper.CartMapper;
import com.htam25.minishop.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CartMapper cartMapper;

    @PostMapping
    public void addToCart(
            @RequestParam Long userId,
            @Valid @RequestBody AddToCartRequest request) {
        cartService.addToCart(userId, request.getProductId(), request.getQuantity());
    }

    @GetMapping
    public List<CartItemResponse> getCart(@RequestParam Long userId) {
        return cartService.getCart(userId)
                .stream()
                .map(cartMapper::toDto)
                .toList();
    }

    @DeleteMapping
    public void clearCart(@RequestParam Long userId) {
        cartService.clearCart(userId);
    }

}
