package com.jay.weatherservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jay.weatherservice.feign.WeatherFeign;
import com.jay.weatherservice.model.Coord;
import com.jay.weatherservice.model.Location;
import com.jay.weatherservice.model.WeatherResponse;
import com.jay.weatherservice.service.WeatherServiceImpl;
@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {

	@Value("${APP_ID}")
	private String APP_ID ;
	
	@Mock
	private WeatherFeign weatherFeign;
	
	@InjectMocks
	private WeatherServiceImpl weatherServiceImpl;
	
	private Location location;
	private List<Location> listLocations;
	private Coord coord;
	@BeforeEach
	void setUp() throws Exception {
		location = new Location("delhi", 21.21, 21.21, "IN", "delhi");
		listLocations = new ArrayList<>();
		listLocations.add(location);
		coord=new Coord();
		coord.setLat(0);
		coord.setLon(0);
	}

	@AfterEach
	void tearDown() throws Exception {
		coord = null;
		location=null;
		listLocations = null;
	}

	@Test
	void testSearch() {
		String city = "delhi";
		when(weatherFeign.searchCity(city, 5, APP_ID)).thenReturn(listLocations);
		assertEquals(listLocations, weatherServiceImpl.search(city));
		verify(weatherFeign).searchCity(city, 5, APP_ID);
	}

	@Test
	void testGetWeather() {
		try {
			when(weatherFeign.getWeather(coord.getLat(),coord.getLon(),APP_ID)).thenReturn(returnWeatherResponse());
			assertEquals(returnWeatherResponse(), weatherServiceImpl.getWeather(coord));
			verify(weatherFeign).getWeather(coord.getLat(), coord.getLon(), APP_ID);
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
	}
	private WeatherResponse returnWeatherResponse() throws JsonMappingException, JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		return om.readValue("{\"cod\":\"200\","
				+ "\"message\":0,\"cnt\":2,"
				+ "\"list\":[{\"dt\":1703527200,"
				+ "\"main\":{\"temp\":27.61,\"feels_like\":30.89,\"temp_min\":27.53,"
				+ "\"temp_max\":27.61,\"pressure\":1010,\"sea_level\":1010,\"grnd_level\":1011,\"humidity\":78,\"temp_kf\":0.08},"
				+ "\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04d\"}],"
				+ "\"wind\":{\"speed\":3.24,\"deg\":215,\"gust\":3.0},"
				+ "\"visibility\":10000,\"pop\":0,\"dt_txt\":\"2023-12-25 18:00:00\"},"
				+ "{\"dt\":1703538000,\"main\":{\"temp\":27.42,\"feels_like\":30.58,\"temp_min\":27.31,\"temp_max\":27.42,"
				+ "\"pressure\":1012,\"sea_level\":1012,\"grnd_level\":1013,\"humidity\":79,\"temp_kf\":0.11},"
				+ "\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04n\"}],"
				+ "\"wind\":{\"speed\":2.73,\"deg\":229,\"gust\":2.6},"
				+ "\"visibility\":10000,\"pop\":0,\"dt_txt\":\"2023-12-25 21:00:00\"}],"
				+ "\"city\":{\"id\":6295630,\"name\":\"Globe\",\"coord\":{\"lat\":0.0,\"lon\":0.0},"
				+ "\"country\":\"\",\"population\":2147483647,\"timezone\":0,\"sunrise\":1703483770,"
				+ "\"sunset\":1703527404}}", WeatherResponse.class);
	}
}
