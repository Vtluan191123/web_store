package com.shop.vtluan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.vtluan.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findByName(String name);
}
