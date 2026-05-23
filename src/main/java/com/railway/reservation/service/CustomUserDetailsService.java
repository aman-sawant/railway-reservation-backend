package com.railway.reservation.service;

import java.util.ArrayList;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.
        UserDetails;

import org.springframework.security.core.userdetails.
        UserDetailsService;

import org.springframework.security.core.userdetails.
        UsernameNotFoundException;

import org.springframework.stereotype.Service;

import com.railway.reservation.entity.User;

import com.railway.reservation.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional =
                userRepository
                        .findByEmail(email);
        if(userOptional.isEmpty()){
            throw new UsernameNotFoundException(
                    "User Not Found"
            );
        }
        User user = userOptional.get();
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                new ArrayList<>()
        );
    }
}