package com.shop.vtluan.service.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.vtluan.model.DTO.UserDto;
import com.shop.vtluan.repository.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Service
public class RegisterValidation implements ConstraintValidator<RegisterChecked, UserDto> {
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext context) {
        boolean valid = true;
        if (!userDto.getPassword().equals(userDto.getConfirm_password())) {
            context.buildConstraintViolationWithTemplate("Nhập lại mật khẩu")
                    .addPropertyNode("confirm_password")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }

        if (this.userRepository.existsByEmail(userDto.getEmail())) {
            context.buildConstraintViolationWithTemplate("Email đã tồn tại")
                    .addPropertyNode("email")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }

        return valid;
    }

}
