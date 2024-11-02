package com.shop.vtluan.service.spec;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.shop.vtluan.model.Products;
import com.shop.vtluan.model.Products_;

public class ProductSpec {

    public static Specification<Products> nameLike(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Products_.NAME), "%" + name + "%");
    }

    public static Specification<Products> minPrice(double price) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.ge(root.get(Products_.PRICE), price);
    }

    public static Specification<Products> maxPrice(double price) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.le(root.get(Products_.PRICE), price);
    }

    public static Specification<Products> equalFactory(String brand) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Products_.BRAND), brand);
    }

    public static Specification<Products> matchBrand(List<String> brand) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.in(root.get(Products_.BRAND)).value(brand);
    }

    public static Specification<Products> matchTarget(List<String> target) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.in(root.get(Products_.TARGET)).value(target);
    }

    public static Specification<Products> matchPrice(double min, double max) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.ge(root.get(Products_.PRICE), min),
                criteriaBuilder.le(root.get(Products_.PRICE), max));
    }
}
