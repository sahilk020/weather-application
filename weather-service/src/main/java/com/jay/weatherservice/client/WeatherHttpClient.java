package com.jay.weatherservice.client;

import com.jay.weatherservice.model.Location;
import com.jay.weatherservice.model.WeatherResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface WeatherHttpClient {
    @GetExchange("/geo/1.0/direct")
    List<Location> searchCity(@RequestParam("q")String city, @RequestParam("limit") int limit, @RequestParam("appid")String appid);

    @GetExchange("/data/2.5/forecast?cnt=5&units=metric")
    WeatherResponse getWeather(@RequestParam("lat")double lat, @RequestParam("lon")double lon, @RequestParam("appid") String appid);
}
