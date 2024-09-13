package com.shop.vtluan.service.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RegisterValidation.class)
public @interface RegisterChecked {
    String message() default "Custom validation error message";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
