package com.bank.controller;

import com.bank.model.LoginRequest;
import com.bank.model.User;
import com.bank.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserService userService;

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody User user) {

        System.out.println("Register API called");

        return ResponseEntity.ok(
                userService.registerUser(user));
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest loginRequest) {

        return ResponseEntity.ok(
                userService.loginUser(loginRequest));
    }

    // TEST
    @GetMapping("/test")
    public String test() {
        return "User API Working";
    }
}