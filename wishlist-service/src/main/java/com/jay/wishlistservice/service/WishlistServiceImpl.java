package com.jay.wishlistservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.jay.wishlistservice.entity.City;
import com.jay.wishlistservice.entity.CityId;
import com.jay.wishlistservice.exception.CityAlreadyExistException;
import com.jay.wishlistservice.exception.CityNotFoundException;
import com.jay.wishlistservice.repository.WishlistRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WishlistServiceImpl implements WishlistService {

	@Autowired
	private WishlistRepository wishlistRepository;

	@Override
	@Cacheable(cacheNames = "wishlist", key="#username")
	public List<City> getAllItems(String username) {
		log.info("getting city from database");
		return wishlistRepository.findByUsername(username);
	}

	@Override
	@CachePut(cacheNames = "wishlist")
	public City save(City city) throws CityAlreadyExistException {
		Optional<City> result = wishlistRepository.findById(new CityId(city.getLat(), city.getLon(),city.getUsername()));
		if (result.isEmpty()) {
			log.info("wishlist saved in database");
			return wishlistRepository.save(city);
		}
			
		throw new CityAlreadyExistException("Can't add to wishlist. It's already present");
	}

	@Override
	@CacheEvict(cacheNames = "wishlist",allEntries = true)
	public City delete(City city) throws CityNotFoundException {
		Optional<City> result = wishlistRepository.findById(new CityId(city.getLat(), city.getLon(),city.getUsername()));
		if (result.isEmpty())
			throw new CityNotFoundException("Can't add to wishlist. It's already present");
		else
			wishlistRepository.delete(city);
		log.info("removed city from database");
		return city;
	}

}
