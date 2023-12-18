package com.jay.authservice.entity;

import lombok.Data;

@Data
public class PasswordResetRequest {
	String username;
	String email;
	String newPassword;
}
