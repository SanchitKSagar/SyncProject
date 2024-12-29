package com.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.helper.APIMessage;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> ResourceNotFoundException( ResourceNotFoundException ex ){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIMessage(ex.getMessage(), HttpStatus.NOT_FOUND.getReasonPhrase()));
	}
	
	@ExceptionHandler(ExistingResourceFound.class)
	public ResponseEntity<Object> ExistingResourceFound( ExistingResourceFound ex ){
		return ResponseEntity.status(HttpStatus.FOUND).body(new APIMessage(ex.getMessage(), HttpStatus.FOUND.getReasonPhrase()));
	}
}
