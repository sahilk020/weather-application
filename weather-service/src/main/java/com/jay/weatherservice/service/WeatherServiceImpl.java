package com.jay.weatherservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.jay.weatherservice.feign.WeatherFeign;
import com.jay.weatherservice.model.Coord;
import com.jay.weatherservice.model.Location;
import com.jay.weatherservice.model.WeatherResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Cacheable(cacheNames = "weather")
public class WeatherServiceImpl implements WeatherService {
	@Value("${APP_ID}")
	private String APP_ID ;
	@Autowired
	private WeatherFeign weatherFeign;
	private static final int limit = 5;

	@Override
	@Cacheable(key = "#city")
	public List<Location> search(String city) {
		log.info("inside method search() ");
		List<Location> searchCity = weatherFeign.searchCity(city,limit, APP_ID);
		log.debug("Found city "+ searchCity.toString());
		return searchCity;
	}

	@Override
	public WeatherResponse getWeather(Coord coord) {
		return weatherFeign.getWeather(coord.getLat(), coord.getLon(), APP_ID);
	}

}
