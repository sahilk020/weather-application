package com.jay.authorizationservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class UserEntity {

	@Id
	private String username;
	@NotBlank(message = "First Name is required")
	private String firstName;
	@NotBlank(message = "Last Name is required")
	private String lastName;
	@NotBlank(message = "Email is required")
	@Email
	private String email;
	@NotBlank(message = "contact number is required")
	@Pattern(regexp = "^[0-9]{10}$", message = "Invalid contact number")
	private String contactNumber;
	@NotBlank
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*#?&]{8,}$", message = "Password must be at least 8 characters long and include at least one letter and one digit")
	private String password;

}