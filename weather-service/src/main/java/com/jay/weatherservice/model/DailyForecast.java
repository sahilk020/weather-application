package com.jay.weatherservice.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class DailyForecast {
	private long dt;
    private long sunrise;
    private long sunset;
    private long moonrise;
    private long moonset;
    private double moon_phase;
    private String summary;
    private TemperatureInfo temp;
    private FeelsLikeInfo feels_like;
    private int pressure;
    private int humidity;
    private double dew_point;
    private double wind_speed;
    private int wind_deg;
    private double wind_gust;
    private List<WeatherInfo> weather;
    private int clouds;
    private double pop;
    private double rain;
    private double uvi;
}
