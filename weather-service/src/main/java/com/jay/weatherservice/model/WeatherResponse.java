package com.jay.weatherservice.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// TODO need to change this model class. some issue are there
public class WeatherResponse {
	private double lat;
    private double lon;
    private String timezone;
    private int timezone_offset;
    private CurrentWeather current;
//    private List<MinutelyForecast> minutely;
//    private List<HourlyForecast> hourly;
    private List<DailyForecast> daily;
    private List<Alert> alerts;
}
