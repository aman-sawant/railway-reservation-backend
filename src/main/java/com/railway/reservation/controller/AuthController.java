package com.railway.reservation.controller;

import com.railway.reservation.dto.LoginRequest;
import com.railway.reservation.dto.SignUpRequest;
import com.railway.reservation.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {
    @Autowired
    private UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignUpRequest request){
        String response=userService.signup(request);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest request){
        String response=userService.login(request);
        return ResponseEntity.ok(response);
    }
}
