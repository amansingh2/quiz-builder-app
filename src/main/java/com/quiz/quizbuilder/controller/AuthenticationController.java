package com.quiz.quizbuilder.controller;

import com.quiz.quizbuilder.dto.LoginRequest;
import com.quiz.quizbuilder.entity.User;
import com.quiz.quizbuilder.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v0")
@Api(value = "Authentication API", tags = "Authentication Apis")
public class AuthenticationController {

    private final IUserService userService;

    @PostMapping("/login")
    @ApiOperation(value = "Login User", notes = "This API is used for user login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }

    @PostMapping("/register")
    @ApiOperation(value = "Register User", notes = "This API is used for registering user")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity.status(201).body(userService.create(user));
    }
}
