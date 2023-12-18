package com.jay.wishlistservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jay.wishlistservice.entity.City;
import com.jay.wishlistservice.entity.CityId;
import com.jay.wishlistservice.exception.CityAlreadyExistException;
import com.jay.wishlistservice.exception.CityNotFoundException;
import com.jay.wishlistservice.repository.WishlistRepository;

@Service
public class WishlistServiceImpl implements WishlistService {

	@Autowired
	private WishlistRepository wishlistRepository;

	@Override
	public List<City> getAllItems() {

		return wishlistRepository.findAll();
	}

	@Override
	public City save(City city) throws CityAlreadyExistException {
		Optional<City> result = wishlistRepository.findById(new CityId(city.getLat(), city.getLon(),city.getUsername()));
		if (result.isEmpty())
			return wishlistRepository.save(city);
		throw new CityAlreadyExistException("Can't add to wishlist. It's already present");
	}

	@Override
	public City delete(City city) throws CityNotFoundException {
		Optional<City> result = wishlistRepository.findById(new CityId(city.getLat(), city.getLon(),city.getUsername()));
		if (result.isEmpty())
			throw new CityNotFoundException("Can't add to wishlist. It's already present");
		else
			wishlistRepository.delete(city);
		return city;
	}

}
