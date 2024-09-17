package com.shop.vtluan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.vtluan.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

}
