package com.maxwell.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maxwell.demo.dto.UserRegisterRequest;
import com.maxwell.demo.model.User;
import com.maxwell.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {
    
    @Autowired
    private UserService userService;

    // email and password 要用 request body 傳遞
    // 1. 資安考量, 較能保護使用者帳號密碼
    // 2. Restful 方法中, 創建對應到 POST
    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {

        Integer userId = userService.register(userRegisterRequest);

        User user = userService.getUserById(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

}
