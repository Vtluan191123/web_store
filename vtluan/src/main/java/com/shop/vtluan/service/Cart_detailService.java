package com.shop.vtluan.service;

import java.lang.StackWalker.Option;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shop.vtluan.model.Cart;
import com.shop.vtluan.model.Cart_detail;
import com.shop.vtluan.model.Products;
import com.shop.vtluan.model.User;
import com.shop.vtluan.repository.Cart_detailRepository;

@Service
public class Cart_detailService {
    private final Cart_detailRepository cart_detailRepository;

    public Cart_detailService(Cart_detailRepository cart_detailRepository) {
        this.cart_detailRepository = cart_detailRepository;
    }

    public void saveCart_detail(Cart_detail cart_detail) {
        this.cart_detailRepository.save(cart_detail);
    }

    public Optional<Cart_detail> checkExistProductAndCart(Products products, Cart cart) {
        return this.cart_detailRepository.findByProductsAndCart(products, cart);
    }
}
