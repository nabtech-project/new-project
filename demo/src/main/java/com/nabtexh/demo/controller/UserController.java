package com.nabtexh.demo.controller;

import com.nabtexh.demo.entity.User;
import com.nabtexh.demo.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        System.out.println("Received User: " + user); // Log incoming user data
        User savedUser = userRepository.save(user);
        System.out.println("Saved User: " + savedUser); // Log saved user
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

}
