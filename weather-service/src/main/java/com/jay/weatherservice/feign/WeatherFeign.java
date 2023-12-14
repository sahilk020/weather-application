package com.jay.weatherservice.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
//need to enter url as well
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jay.weatherservice.model.Location;
import com.jay.weatherservice.model.WeatherResponse;
@FeignClient(name = "open-weather-api",url = "https://api.openweathermap.org")
public interface WeatherFeign {
	@GetMapping("/geo/1.0/direct")
	List<Location> searchCity(@RequestParam("q")String city,@RequestParam("limit") int limit,@RequestParam("appid")String appid);
	
	@GetMapping("/data/2.5/forecast")
	WeatherResponse getWeather(@RequestParam("lat")String lat,@RequestParam("lon")String lon,@RequestParam("appid") String appid);
}
