package com.jay.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jay.authservice.entity.UserCredentials;

public interface AuthRepository extends JpaRepository<UserCredentials, String> {

}
