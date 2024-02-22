package com.jay.authservice.entity;

import org.springframework.http.HttpStatus;

public record ErrorResponse(HttpStatus statusCode,String message) {
}
