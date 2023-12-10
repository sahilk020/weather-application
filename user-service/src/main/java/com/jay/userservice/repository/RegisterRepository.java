package com.jay.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jay.userservice.entity.User;

public interface RegisterRepository extends JpaRepository<User, String> {

}
