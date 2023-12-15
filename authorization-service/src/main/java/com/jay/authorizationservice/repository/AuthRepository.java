package com.jay.authorizationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jay.authorizationservice.entity.UserEntity;
@Repository
public interface AuthRepository extends JpaRepository<UserEntity, String>{

}
