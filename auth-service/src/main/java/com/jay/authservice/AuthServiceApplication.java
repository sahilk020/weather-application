package com.jay.authservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
public class AuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}
	
	@Bean
	public OpenAPI AuthServiceOpenAPI() {
		String description = "This API lets users perform login and save data received through Kafka";
		String email = "jaydeo.sahu@cognizant.com";
		return new OpenAPI().info(new Info().title("Auth Service").description(description)
				.contact(new Contact().email(email).name("Jaydeo Sahu").url("jaysahu.com")));
	}

}
