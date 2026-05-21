package com.railway.reservation.service;

import com.railway.reservation.dto.LoginRequest;
import com.railway.reservation.dto.SignUpRequest;
import com.railway.reservation.entity.User;
import com.railway.reservation.repository.UserRepository;
import com.railway.reservation.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    public String signup(SignUpRequest request){
        Optional<User> existingUser=userRepository.findByEmail(request.getEmail());
        if(existingUser.isPresent()){
            return "Email already exists";
        }
        User user=new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");
        userRepository.save(user);
        return "Signup Successful";
    }

    public String login(LoginRequest request){
        Optional<User> existingUser=userRepository.findByEmail(request.getEmail());
        if(existingUser.isEmpty()){
            return "User Not Found";
        }
        if(!passwordEncoder.matches(request.getPassword(),existingUser.get().getPassword())){
            return "Invalid Password";
        }
        return jwtUtil.generateToken(existingUser.get().getEmail());
    }
}
