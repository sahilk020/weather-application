package com.jay.wishlistservice.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class Coordinate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double lat;
	private double lon;

}
