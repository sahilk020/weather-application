package com.jay.weatherservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
@EnableFeignClients
public class WeatherServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherServiceApplication.class, args);
	}

	@Bean
	public OpenAPI weatherServiceOpenAPI() {
		String description = "This API communicates with third party api to fetch weather details and search of cities weather";
		String email = "jaydeo.sahu@cognizant.com";
		return new OpenAPI().info(new Info().title("Weather Service").description(description)
				.contact(new Contact().email(email).name("Jaydeo Sahu").url("jaysahu.com")));
	}
}
