package com.shop.vtluan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.vtluan.model.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

}
