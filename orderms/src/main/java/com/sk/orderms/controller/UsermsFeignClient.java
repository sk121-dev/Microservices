package com.sk.orderms.controller;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sk.orderms.vo.UserEntity;

@FeignClient(name = "userms", url = "http://localhost:8081/api/v1")
public interface UsermsFeignClient {

    @GetMapping("/users")
    public List<UserEntity> getAllUsers();

    @GetMapping("/user/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable("id") Long id);
    
}
