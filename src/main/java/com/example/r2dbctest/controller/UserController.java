package com.example.r2dbctest.controller;

import com.example.r2dbctest.entity.User;
import com.example.r2dbctest.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class UserController {
    private final UserRepository userRepository;

    @GetMapping("/users")
    public Flux<User>  users()  {
        return userRepository.findAll();
    }

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
