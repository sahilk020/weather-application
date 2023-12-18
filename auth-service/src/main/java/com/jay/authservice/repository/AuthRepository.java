package com.jay.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jay.authservice.entity.UserEntity;

public interface AuthRepository extends JpaRepository<UserEntity, String> {

}
