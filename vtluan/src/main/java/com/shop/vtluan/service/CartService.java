package com.shop.vtluan.service;

import org.springframework.stereotype.Service;

import com.shop.vtluan.model.Cart;
import com.shop.vtluan.repository.CartRepository;

@Service
public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart saveCart(Cart cart) {
        return this.cartRepository.save(cart);
    }
}
