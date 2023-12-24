package com.jay.userservice.exceptionHandler;

import com.jay.userservice.exception.UserAlreadyRegisteredException;
import com.jay.userservice.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BindException.class)
	public ResponseEntity<ErrorResponse> handleValidationException(BindException ex) {
		// Handle validation errors globally
		StringBuilder errorMessage = new StringBuilder("Validation errors: ");
		ex.getBindingResult().getAllErrors()
				.forEach(error -> errorMessage.append(error.getDefaultMessage()).append("; "));
		ErrorResponse errorResponse = new ErrorResponse(errorMessage.toString(),HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	@ExceptionHandler(UserAlreadyRegisteredException.class)
	public ResponseEntity<ErrorResponse> handleUserAlreadyRegisteredException(UserAlreadyRegisteredException ex){
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(),HttpStatus.CONFLICT);
		return ResponseEntity.status(errorResponse.getHttpStatus()).body(errorResponse);
	}
}
