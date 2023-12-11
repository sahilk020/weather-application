package com.jay.authorizationservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SuccessResponse {
	private String username;
	private String token;
}
