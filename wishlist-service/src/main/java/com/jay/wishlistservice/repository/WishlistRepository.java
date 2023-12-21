package com.jay.wishlistservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jay.wishlistservice.entity.City;
import com.jay.wishlistservice.entity.CityId;

public interface WishlistRepository extends JpaRepository<City, CityId> {
	List<City> findByUsername(String username);
}
