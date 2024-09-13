package com.shop.vtluan.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NotNull(message = "khong duoc de trong")
    @Size(min = 3, message = "Nhập tối thiểu 3 kí tự")
    String email;

    @NotNull(message = "khong duoc de trong")
    @Size(min = 3, message = "Nhập tối thiểu 3 kí tự")
    String name;

    @NotNull(message = "khong duoc de trong")
    @Size(min = 3, message = "Nhập tối thiểu 3 kí tự")
    String password;

    @NotNull(message = "khong duoc de trong")
    @Size(min = 3, message = "Nhập tối thiểu 3 kí tự")
    String phone_number;

    String image;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    private String token;

    @OneToOne(mappedBy = "user")
    Cart cart;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", email=" + email + ", name=" + name + ", password=" + password + ", phone_number="
                + phone_number + ", image=" + image + ", role=" + role + "]";
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
