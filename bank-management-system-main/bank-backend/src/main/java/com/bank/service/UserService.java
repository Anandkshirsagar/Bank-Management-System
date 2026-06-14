package com.bank.service;

import com.bank.model.LoginRequest;
import com.bank.model.LoginResponse;
import com.bank.model.User;
import com.bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // REGISTER
    public String registerUser(User user) {

        System.out.println("Received User: " + user);

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException(
                    "Email already registered");
        }

        if (user.getRole() == null ||
                user.getRole().isEmpty()) {

            user.setRole("USER");
        }

        userRepository.save(user);

        System.out.println("User Saved Successfully");

        return "User registered successfully!";
    }

    // LOGIN
    public LoginResponse loginUser(
            LoginRequest loginRequest) {

        User user = userRepository
                .findByEmail(loginRequest.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (!user.getPassword()
                .equals(loginRequest.getPassword())) {

            throw new RuntimeException("Invalid password");
        }

        return new LoginResponse(
                "dummy-token",
                user.getEmail(),
                user.getFullName(),
                user.getRole(),
                "Login successful"
        );
    }

    // GET ALL USERS
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // GET USER BY ID
    public User getUserById(int id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found with id: " + id));
    }

    // DELETE USER
    public String deleteUser(int id) {

        userRepository.deleteById(id);

        return "User deleted successfully";
    }
}