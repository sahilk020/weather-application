package com.jay.wishlistservice.service;

import java.util.List;

import com.jay.wishlistservice.entity.CityDTO;
import com.jay.wishlistservice.exception.CityAlreadyExistException;
import com.jay.wishlistservice.exception.CityNotFoundException;

public interface WishlistService {
	List<CityDTO> getAllItems(String username);
	CityDTO save(CityDTO city) throws CityAlreadyExistException;
	CityDTO delete(CityDTO city) throws CityNotFoundException;
}
