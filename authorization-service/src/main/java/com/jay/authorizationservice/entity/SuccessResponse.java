package com.jay.authorizationservice.entity;

import lombok.Data;

@Data
public class SuccessResponse {
	private String username;
	private String token;
}
