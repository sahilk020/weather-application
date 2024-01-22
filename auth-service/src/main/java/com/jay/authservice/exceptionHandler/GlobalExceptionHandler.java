package com.jay.authservice.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jay.authservice.entity.ErrorResponse;
import com.jay.authservice.exception.LoginException;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<ErrorResponse> handleLoginException(LoginException loginException){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST,loginException.getMessage()));
	}
}
