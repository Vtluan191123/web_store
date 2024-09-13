package com.shop.vtluan.model.DTO;

import com.shop.vtluan.service.validation.RegisterChecked;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@RegisterChecked
public class UserDto {

    @NotNull(message = "khong duoc de trong")
    @Size(min = 3, message = "Nhập tối thiểu 3 kí tự")
    String name;

    @NotNull(message = "khong duoc de trong")
    @Size(min = 3, message = "Nhập tối thiểu 3 kí tự")
    String email;

    @NotNull(message = "khong duoc de trong")
    @Size(min = 3, message = "Nhập tối thiểu 3 kí tự")
    String phone_number;

    @NotNull(message = "khong duoc de trong")
    @Size(min = 3, message = "Nhập tối thiểu 3 kí tự")
    String password;

    String confirm_password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }
}
