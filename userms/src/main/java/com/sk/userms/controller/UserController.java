package com.sk.userms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.userms.entity.UserEntity;
import com.sk.userms.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    public static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> getAllUsers(){
        log.info("Received request to fetch all users.");
        ResponseEntity<List<UserEntity>> response = userService.getAllUsers();

        return response;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable("id") Long id){
        log.info("Received request to fetch user at id {}",id);
        ResponseEntity<UserEntity> response = userService.getUserById(id);

        return response;
    }
    
}
