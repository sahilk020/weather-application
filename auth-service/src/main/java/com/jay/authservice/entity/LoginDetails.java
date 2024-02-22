package com.jay.authservice.entity;

import jakarta.validation.constraints.NotBlank;

public record LoginDetails(@NotBlank(message = "username is required") String username,
						   @NotBlank(message = "password is required") String password) {
}
