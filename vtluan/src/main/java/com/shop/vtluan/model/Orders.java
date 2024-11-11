package com.shop.vtluan.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    double totalPrice;
    String infor;
    String status;

    public Orders(long id, double totalPrice, String infor, String status, User user,
            List<Order_detail> order_details) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.infor = infor;
        this.status = status;
        this.user = user;
        this.order_details = order_details;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Order_detail> getOrder_details() {
        return order_details;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "orders")
    List<Order_detail> order_details;

    public Orders() {
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double toltalPrice) {
        this.totalPrice = toltalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setOrder_details(List<Order_detail> order_details) {
        this.order_details = order_details;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInfor() {
        return infor;
    }

    public void setInfor(String infor) {
        this.infor = infor;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
