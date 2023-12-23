package com.jay.wishlistservice.entity;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6780158649217665570L;
	@NotBlank
	private String city;
	@NotBlank
	private String country;
	@NotBlank
	private double lat;
	@NotBlank
	private double lon;
	@NotBlank
	private String username;
}
