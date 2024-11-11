package com.shop.vtluan.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shop.vtluan.model.Orders;
import com.shop.vtluan.repository.OrdersRepository;

@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;

    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public void updateOrder(Orders order) {
        this.ordersRepository.save(order);
    }

    public Optional<Orders> getOrderById(long id) {
        return this.ordersRepository.findById(id);
    }

    public void deleteOrder(Orders orders) {
        this.ordersRepository.delete(orders);
    }
}
