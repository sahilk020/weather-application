package com.jay.wishlistservice.service;

import java.util.List;

import com.jay.wishlistservice.entity.City;
import com.jay.wishlistservice.exception.CityAlreadyExistException;
import com.jay.wishlistservice.exception.CityNotFoundException;

public interface WishlistService {
	List<City> getAllItems();
	City save(City city) throws CityAlreadyExistException;
	City delete(City city) throws CityNotFoundException;
}
