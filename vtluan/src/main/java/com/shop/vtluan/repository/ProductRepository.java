package com.shop.vtluan.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.shop.vtluan.model.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long>, JpaSpecificationExecutor<Products> {
    @SuppressWarnings("null")
    List<Products> findAll();

    Optional<Products> findById(long id);

    @SuppressWarnings("null")
    Page<Products> findAll(Pageable pageable);

    @SuppressWarnings("null")
    Page<Products> findAll(Specification<Products> specification, Pageable pageable);

    List<Products> findByNameContainingIgnoreCase(String name);

}
