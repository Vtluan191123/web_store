package com.shop.vtluan.model.DTO;

import java.util.List;
import java.util.Optional;

public class ProductsCriteriaDto {
    Optional<String> page = Optional.ofNullable("1");
    Optional<List<String>> brand;
    Optional<List<String>> target;
    Optional<List<String>> price;
    Optional<String> sort = Optional.ofNullable("normal");

    public Optional<String> getPage() {
        return page;
    }

    public void setPage(Optional<String> page) {
        this.page = page;
    }

    public Optional<List<String>> getBrand() {
        return brand;
    }

    public void setBrand(Optional<List<String>> brand) {
        this.brand = brand;
    }

    public Optional<List<String>> getTarget() {
        return target;
    }

    public void setTarget(Optional<List<String>> target) {
        this.target = target;
    }

    public Optional<List<String>> getPrice() {
        return price;
    }

    public void setPrice(Optional<List<String>> price) {
        this.price = price;
    }

    public Optional<String> getSort() {
        return sort;
    }

    public void setSort(Optional<String> sort) {
        this.sort = sort;
    }

}
