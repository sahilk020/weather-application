package com.jay.weatherapigateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import com.jay.weatherapigateway.filter.ApiFilter;

@SpringBootApplication
public class WeatherApiGatewayApplication {
	@Autowired
	private ApiFilter apiFilter;

	public static void main(String[] args) {
		SpringApplication.run(WeatherApiGatewayApplication.class, args);
	}
	@Bean
	public RouteLocator apiRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(route-> route.path("/api/wishlist/**").filters(f->f.filter(apiFilter)).uri("lb://wishlist-service"))
				.route(route->route.path("/api/weather/**").filters(f->f.filter(apiFilter)).uri("lb://weather-service"))
				.route(route->route.path("/api/users/**").uri("lb://user-service"))
				.route(route->route.path("/api/auth/**").uri("lb://authorization-service"))
				.build();
	}
}
