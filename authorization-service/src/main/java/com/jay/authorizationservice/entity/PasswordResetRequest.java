package com.jay.authorizationservice.entity;

import lombok.Data;

@Data
public class PasswordResetRequest {
	String username;
	String email;
	String newPassword;
}
