package com.htam25.minishop.controller;

import com.htam25.minishop.dto.request.AddToCartRequest;
import com.htam25.minishop.dto.request.UpdateCartItemRequest;
import com.htam25.minishop.dto.response.CartItemResponse;
import com.htam25.minishop.mapper.CartMapper;
import com.htam25.minishop.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CartMapper cartMapper;

    @PutMapping("/items/{id}")
    public ResponseEntity<?> updateCartItem(
            @PathVariable Long id,
            @RequestBody @Valid UpdateCartItemRequest request
    ) {
        cartService.updateCartItem(id, request.getQuantity());
        return ResponseEntity.ok("Updated successfully");
    }

    @PostMapping
    public void addToCart(@Valid @RequestBody AddToCartRequest request) {
        cartService.addToCart(request.getProductId(), request.getQuantity());
    }

    @GetMapping
    public List<CartItemResponse> getCart() {
        return cartService.getCart()
                .stream()
                .map(cartMapper::toDto)
                .toList();
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<?> removeCartItem(@PathVariable Long id) {
        cartService.removeFromCart(id);
        return ResponseEntity.ok("Item removed from cart");
    }
    @DeleteMapping
    public void clearCart() {
        cartService.clearCart();
    }

}
