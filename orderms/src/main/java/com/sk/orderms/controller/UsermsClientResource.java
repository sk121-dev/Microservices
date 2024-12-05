package com.sk.orderms.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.sk.orderms.vo.UserEntity;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import reactor.core.publisher.Mono;


@RestController
public class UsermsClientResource {

    public static final Logger log = LoggerFactory.getLogger(UsermsClientResource.class);

    @Autowired
    private WebClient webClient;

    @Autowired
    private UsermsFeignClient usermsFeignClient;
    
    public UsermsClientResource(UsermsFeignClient usermsFeignClient){
        this.usermsFeignClient = usermsFeignClient;
    }

    @GetMapping("/users")
    @CircuitBreaker(name = "usermsclient", fallbackMethod = "usermsfallback")
    public Mono<Object> fetchAllUsersFromUserms(){
        log.info("calling userms fron orderms for getting all users");

        return webClient.get().uri("/api/v1/users").retrieve().bodyToMono(Object.class);
    }

    private Mono<Object> usermsfallback(){
        return Mono.just("CalledFromCache"); 
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserEntity> fetchUserByUserId(@PathVariable("id") Long id) {
        return usermsFeignClient.getUserById(id);
    }
    
}
