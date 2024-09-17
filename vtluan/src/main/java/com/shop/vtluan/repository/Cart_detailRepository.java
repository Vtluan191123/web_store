package com.shop.vtluan.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.vtluan.model.Cart;
import com.shop.vtluan.model.Cart_detail;
import com.shop.vtluan.model.Products;

@Repository
public interface Cart_detailRepository extends JpaRepository<Cart_detail, Long> {

    Optional<Cart_detail> findByProductsAndCart(Products products, Cart cart);

    List<Cart_detail> findByCart(Cart cart);
}