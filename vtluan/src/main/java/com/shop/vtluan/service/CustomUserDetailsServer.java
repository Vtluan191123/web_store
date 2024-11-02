package com.shop.vtluan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.shop.vtluan.repository.UserRepository;

@Component("userDetailsService")
public class CustomUserDetailsServer implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    public CustomUserDetailsServer() {
    };

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.shop.vtluan.model.User user = this.userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        var userDetail = org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().getName())
                .build();

        return userDetail;

    }

}
