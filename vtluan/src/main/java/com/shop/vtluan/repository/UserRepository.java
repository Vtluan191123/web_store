package com.shop.vtluan.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.vtluan.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByNameContainingIgnoreCase(String name);

    User findByEmail(String email);

    User findByName(String name);

    boolean existsByEmail(String email);

    Page<User> findAll(Pageable pageable);

}