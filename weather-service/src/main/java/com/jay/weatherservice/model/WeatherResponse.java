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
	private String cod;
    private int message;
    private int cnt;
    private List<WeatherData> list;
    private City city;
}
