package com.jay.wishlistservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jay.wishlistservice.entity.City;
import com.jay.wishlistservice.entity.CityDTO;
import com.jay.wishlistservice.entity.CityId;
import com.jay.wishlistservice.exception.CityAlreadyExistException;
import com.jay.wishlistservice.exception.CityNotFoundException;
import com.jay.wishlistservice.repository.WishlistRepository;
@ExtendWith(MockitoExtension.class)
class WishlistServiceTest {
	@Mock
	private WishlistRepository wishlistRepository;
	@InjectMocks
	private WishlistServiceImpl wishlistServiceImpl;

	private CityDTO cityDTO;
	private City city;
	private List<CityDTO> citiesDTO;
	private List<City> cities;
	private CityId cityid;
	@BeforeEach
	void setUp() throws Exception {
		cityDTO = new CityDTO("sample city", "sample county", 1, 1, "sample user");
		city = new City("sample city", "sample county", 1, 1, "sample user");
		cityid = new CityId(cityDTO.getLat(),cityDTO.getLon(),cityDTO.getUsername());
		cities = new ArrayList<>();
		cities.add(city);
		citiesDTO = new ArrayList<>();
		citiesDTO.add(cityDTO);
	}

	@AfterEach
	void tearDown() throws Exception {
		cityDTO=null;
		cities=null;
	}

	@Test
	void testGetAllItems() {
		when(wishlistRepository.findByUsername(anyString())).thenReturn(cities);
		assertEquals(citiesDTO, wishlistServiceImpl.getAllItems(anyString()));
		verify(wishlistRepository).findByUsername(anyString());
	}

	@Test
	void testSaveSuccess() throws CityAlreadyExistException {
		when(wishlistRepository.findById(cityid)).thenReturn(Optional.empty());
		when(wishlistRepository.save(city)).thenReturn(city);
		assertEquals(cityDTO, wishlistServiceImpl.save(cityDTO));
		verify(wishlistRepository).findById(cityid);
		verify(wishlistRepository).save(city);
	}
	
	@Test
	void testSaveThrowsException() throws CityAlreadyExistException {
		when(wishlistRepository.findById(cityid)).thenReturn(Optional.of(city));
		assertThrows(CityAlreadyExistException.class,()-> wishlistServiceImpl.save(cityDTO));
		verify(wishlistRepository).findById(cityid);
	}

	@Test
	void testDeleteSuccess() throws CityNotFoundException {
		when(wishlistRepository.findById(cityid)).thenReturn(Optional.of(city));
		assertEquals(cityDTO, wishlistServiceImpl.delete(cityDTO));
	}

	@Test
	void testDeleteThrowsException() throws CityNotFoundException {
		when(wishlistRepository.findById(cityid)).thenReturn(Optional.empty());
		assertThrows(CityNotFoundException.class, ()->wishlistServiceImpl.delete(cityDTO));
	}
}
