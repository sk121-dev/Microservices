package com.sk.userms.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sk.userms.entity.UserEntity;
import com.sk.userms.exception.ResourceNotFoundException;
import com.sk.userms.repository.UserRepository;


@Service
public class UserService {
    public static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepo;

    public ResponseEntity<List<UserEntity>> getAllUsers(){
        log.info("Fetching all users from user_info");
        
        try{
            List<UserEntity> users = userRepo.findAll();
            if(users.isEmpty()){
                log.warn("No user found in the user_info");
                return ResponseEntity.noContent().build();
            }
            log.info("Successfully fetched {} users",users.size());
            return ResponseEntity.ok(users);
        }catch(Exception ex){
            log.error("Error occurred while fetching users: {}", ex.getMessage());
            throw new RuntimeException("Unable to fetch users at the moment.",ex);
        }

    }

    public ResponseEntity<UserEntity> getUserById(Long id) {
        log.info("Fetching user with ID: {}", id);

        try{
            Optional<UserEntity> user = userRepo.findById(id);
            if(user.isPresent()){
                return ResponseEntity.ok(user.get());
            }
            else{
                throw new ResourceNotFoundException("User not found with ID: "+id);
            }

        }catch(Exception ex){
            log.error("Error while fetching user with ID: {}",id,ex);

            throw ex;
        }
    }
    
}
