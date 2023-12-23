package com.jay.wishlistservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.jay.wishlistservice.entity.City;
import com.jay.wishlistservice.entity.CityDTO;
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
	@Cacheable(cacheNames = "cityList", key = "#username")
	public List<CityDTO> getAllItems(String username) {
		log.info("getting city from database");

		return wishlistRepository.findByUsername(username).stream().map(city -> CityDTO.builder().city(city.getCity())
				.country(city.getCountry()).lat(city.getLat()).lon(city.getLon()).username(city.getUsername()).build())
				.toList();
	}

	@Override
	@CachePut(cacheNames = "cityCache", key = "#cityDTO.username")
	public CityDTO save(CityDTO cityDTO) throws CityAlreadyExistException {
		Optional<City> result = wishlistRepository
				.findById(new CityId(cityDTO.getLat(), cityDTO.getLon(), cityDTO.getUsername()));
		if (result.isEmpty()) {
			City city = City.builder().city(cityDTO.getCity()).country(cityDTO.getCountry()).lat(cityDTO.getLat())
					.lon(cityDTO.getLon()).username(cityDTO.getUsername()).build();
			log.info("wishlist saved in database");
			City response = wishlistRepository.save(city);
			CityDTO responseDTO = CityDTO.builder().city(response.getCity()).country(response.getCountry()).lat(response.getLat())
					.lon(response.getLon()).username(response.getUsername()).build();
			return responseDTO;
		}

		throw new CityAlreadyExistException("Can't add to wishlist. It's already present");
	}

	@Override
	@CacheEvict(cacheNames = "cityCache", allEntries = true)
	public CityDTO delete(CityDTO cityDTO) throws CityNotFoundException {
		Optional<City> result = wishlistRepository
				.findById(new CityId(cityDTO.getLat(), cityDTO.getLon(), cityDTO.getUsername()));
		if (result.isEmpty())
			throw new CityNotFoundException("Can't delete. city not found");
		else {
			City city = City.builder().city(cityDTO.getCity()).country(cityDTO.getCountry()).lat(cityDTO.getLat())
					.lon(cityDTO.getLon()).username(cityDTO.getUsername()).build();
			wishlistRepository.delete(city);
		}
		log.info("removed city from database");
		return cityDTO;
	}

}
