package com.jay.userservice.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BindException.class)
	public ResponseEntity<String> handleValidationException(BindException ex) {
		// Handle validation errors globally
		StringBuilder errorMessage = new StringBuilder("Validation errors: ");
		ex.getBindingResult().getAllErrors()
				.forEach(error -> errorMessage.append(error.getDefaultMessage()).append("; "));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage.toString());
	}
}
