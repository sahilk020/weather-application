package com.jay.wishlistservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
public class WishlistServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WishlistServiceApplication.class, args);
	}
	
	@Bean
	public OpenAPI wishlistServiceOpenAPI() {
		String description = "This API lets users keep a collection of favourite cities";
		String email = "jaydeo.sahu@cognizant.com";
		return new OpenAPI().info(new Info().title("Wishlist Service").description(description)
				.contact(new Contact().email(email).name("Jaydeo Sahu").url("jaysahu.com")));
	}

}
