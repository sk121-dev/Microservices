package com.sk.userms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sk.userms.entity.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    
}
