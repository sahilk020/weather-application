package com.jay.authorizationservice.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDetails {
	@NotBlank(message = "username is required")
	private String username;
	@NotBlank(message = "password is required")
	private String password;
}
