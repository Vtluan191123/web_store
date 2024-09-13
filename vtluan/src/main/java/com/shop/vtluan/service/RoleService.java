package com.shop.vtluan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shop.vtluan.model.Role;
import com.shop.vtluan.repository.RoleRepository;

@Service
public class RoleService {
    RoleRepository rolerepository;

    public RoleService(RoleRepository rolerepository) {
        this.rolerepository = rolerepository;
    }

    public List<Role> getRoles() {
        return this.rolerepository.findAll();
    }

    public Role findRolebyName(String name) {
        return this.rolerepository.findByName(name);
    }

    public Optional<Role> findRoleById(long id) {
        return this.rolerepository.findById(id);
    }
}
