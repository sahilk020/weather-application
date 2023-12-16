package com.jay.weatherservice.model;

import java.util.List;

import lombok.Data;

@Data
public class WeatherData {
	private long dt;
    private Main main;
    private List<Weather> weather;
//    private Clouds clouds;
    private Wind wind;
    private int visibility;
    private int pop;
//    private Sys sys;
    private String dt_txt;
}
