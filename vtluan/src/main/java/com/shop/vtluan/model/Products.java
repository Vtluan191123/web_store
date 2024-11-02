package com.shop.vtluan.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NotNull(message = "khong duoc de trong")
    @Size(min = 3, message = "Nhập tối thiểu 3 kí tự")
    String name;

    @NotNull(message = "khong duoc de trong")
    @Size(min = 3, message = "Nhập tối thiểu 3 kí tự")
    String brand;

    @NotNull(message = "khong duoc de trong")
    @Size(min = 3, message = "Nhập tối thiểu 3 kí tự")
    @Column(columnDefinition = "TEXT")
    String description;

    @NotNull(message = "khong duoc de trong")
    @Size(min = 3, message = "Nhập tối thiểu 3 kí tự")
    String des_short;

    @NotNull(message = "khong duoc de trong")
    @Size(min = 3, message = "Nhập tối thiểu 3 kí tự")
    double price;

    @NotNull(message = "khong duoc de trong")
    @Size(min = 3, message = "Nhập tối thiểu 3 kí tự")
    String target;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    String image;

    @OneToMany(mappedBy = "products")
    List<Cart_detail> cart_details;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Cart_detail> getCart_details() {
        return cart_details;
    }

    public void setCart_details(List<Cart_detail> cart_details) {
        this.cart_details = cart_details;
    }

    public String getDes_short() {
        return des_short;
    }

    public void setDes_short(String des_short) {
        this.des_short = des_short;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
