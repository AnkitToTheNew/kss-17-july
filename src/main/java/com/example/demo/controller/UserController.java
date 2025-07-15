package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public String addUser(@RequestBody User user) {
        userRepository.save(user);
        return "User saved successfully!";
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
