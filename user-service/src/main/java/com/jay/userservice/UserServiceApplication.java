package com.jay.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
	@Bean
	public OpenAPI userServiceOpenAPI() {
		String description = "This API lets users register which then sends the data through Kafka for further operation";
		String email = "jaydeo.sahu@cognizant.com";
		return new OpenAPI().info(new Info().title("User Service").description(description)
				.contact(new Contact().email(email).name("Jaydeo Sahu").url("jaysahu.com")));
	}
}
