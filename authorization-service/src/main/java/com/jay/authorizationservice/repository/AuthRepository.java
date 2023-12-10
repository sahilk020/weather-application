package com.jay.authorizationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jay.authorizationservice.entity.UserEntity;

public interface AuthRepository extends JpaRepository<UserEntity, String>{

}
