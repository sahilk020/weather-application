package com.jay.weatherservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jay.weatherservice.model.Coordinates;
import com.jay.weatherservice.model.Location;
import com.jay.weatherservice.model.WeatherResponse;

public interface WeatherService {
	List<Location> search(String city);

	WeatherResponse getWeather(Coordinates coordinates);
}
