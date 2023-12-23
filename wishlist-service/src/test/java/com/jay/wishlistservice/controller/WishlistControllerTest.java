package com.jay.wishlistservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.jay.wishlistservice.entity.CityDTO;
import com.jay.wishlistservice.exception.CityAlreadyExistException;
import com.jay.wishlistservice.exception.CityNotFoundException;
import com.jay.wishlistservice.service.WishlistServiceImpl;
@ExtendWith(MockitoExtension.class)
class WishlistControllerTest {
	@Mock
	private WishlistServiceImpl wishlistServiceImpl;
	@InjectMocks
	private WishlistController wishlistController;
	
	private CityDTO cityDTO;
	private List<CityDTO> cities;
	@BeforeEach
	void setUp() {
		cityDTO = new CityDTO("sample city", "sample county", 1, 1, "sample user");
		cities = new ArrayList<>();
		cities.add(cityDTO);
	}
	@Test
	void testSave() {
		try {
			when(wishlistServiceImpl.save(cityDTO)).thenReturn(cityDTO);
			ResponseEntity<CityDTO> response = wishlistController.save(cityDTO);
			assertTrue(response.getStatusCode().is2xxSuccessful());
			assertEquals(response.getBody(), cityDTO);
		} catch (CityAlreadyExistException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void givenCityDtoWhenSaveThenThrowException() {
		try {
			when(wishlistServiceImpl.save(cityDTO)).thenThrow(CityAlreadyExistException.class);
			assertThrows(CityAlreadyExistException.class, ()->wishlistController.save(cityDTO));
		} catch (CityAlreadyExistException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testDelete() {
		try {
			when(wishlistServiceImpl.delete(cityDTO)).thenReturn(cityDTO);
			assertEquals(wishlistController.delete(cityDTO).getBody(), cityDTO);
		} catch (CityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testDeleteThrowException() {
		try {
			when(wishlistServiceImpl.delete(cityDTO)).thenThrow(CityNotFoundException.class);
			assertThrows(CityNotFoundException.class, ()->wishlistController.delete(cityDTO));
		} catch (CityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testGetAllItems() {
		when(wishlistServiceImpl.getAllItems(anyString())).thenReturn(cities);
		assertEquals(cities, wishlistController.getAllItems(anyString()).getBody());
	}

}
