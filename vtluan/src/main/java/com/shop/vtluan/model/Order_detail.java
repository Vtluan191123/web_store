package com.shop.vtluan.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Order_detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    int quantity;
    @ManyToOne
    @JoinColumn(name = "orders_id")
    Orders orders;

    @ManyToOne
    @JoinColumn(name = "products_id")
    Products products;

    public Order_detail(long id, int quantity, Orders orders, Products products) {
        this.id = id;
        this.quantity = quantity;
        this.orders = orders;
        this.products = products;
    }

    public Order_detail() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

}
