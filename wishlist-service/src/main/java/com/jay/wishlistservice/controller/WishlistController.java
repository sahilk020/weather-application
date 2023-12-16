package com.jay.wishlistservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jay.wishlistservice.entity.City;
import com.jay.wishlistservice.exception.CityAlreadyExistException;
import com.jay.wishlistservice.exception.CityNotFoundException;
import com.jay.wishlistservice.service.WishlistService;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

	@Autowired
	private WishlistService wishlistService;

	@PostMapping("/save")
	ResponseEntity<City> save(@RequestBody City city) throws CityAlreadyExistException {
		return ResponseEntity.ok(wishlistService.save(city));

	}

	@DeleteMapping("/delete")
	ResponseEntity<City> delete(@RequestBody City city) throws CityNotFoundException {
		return ResponseEntity.ok(wishlistService.delete(city));
	}
	
	@GetMapping
	ResponseEntity<List<City>> getAllItems(){
		return ResponseEntity.ok(wishlistService.getAllItems());
	}
	
}
