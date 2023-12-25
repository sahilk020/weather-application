package com.jay.weatherservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jay.weatherservice.model.Coord;
import com.jay.weatherservice.model.Location;
import com.jay.weatherservice.model.WeatherResponse;
import com.jay.weatherservice.service.WeatherService;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

	@Autowired
	private WeatherService weatherService;

	@GetMapping("/search/{city}")
	public ResponseEntity<List<Location>> search(@PathVariable String city) {
		return ResponseEntity.ok(weatherService.search(city));
	}
	@GetMapping("/current")
	public ResponseEntity<WeatherResponse> getWeather(@RequestBody Coord coordinates){
		return ResponseEntity.ok(weatherService.getWeather(coordinates));
	}

}
