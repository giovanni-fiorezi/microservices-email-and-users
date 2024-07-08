package com.microservices.user.controllers;

import com.microservices.user.dtos.UserDto;
import com.microservices.user.models.User;
import com.microservices.user.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody @Valid UserDto userDto) {
        User user = userService.save(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
