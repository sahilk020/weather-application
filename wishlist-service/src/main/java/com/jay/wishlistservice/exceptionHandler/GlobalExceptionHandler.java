package com.jay.wishlistservice.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jay.wishlistservice.exception.CityAlreadyExistException;
import com.jay.wishlistservice.exception.CityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(CityAlreadyExistException.class)
	public ResponseEntity<String> cityAlreadyExistExceptionHandler(CityAlreadyExistException ex){
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
	
	@ExceptionHandler(CityNotFoundException.class)
	public ResponseEntity<String> cityNotFoundExceptionHandler(CityNotFoundException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
}
