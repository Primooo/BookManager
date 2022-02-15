package com.jinwon.bookmanager.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jinwon.bookmanager.exception_handler.exceptions.BookNotFoundException;
import com.jinwon.bookmanager.exception_handler.exceptions.SearchTypeInvalidException;


@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleArgumentValidException(MethodArgumentNotValidException e) {
		ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), 
				"ARG-INVALID-ERROR",
				e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleBookNotFoundException(BookNotFoundException e) {
		ErrorResponse response = new ErrorResponse(e.getErrorCode());
		return new ResponseEntity<>(response, HttpStatus.valueOf(e.getErrorCode().getStatus()));
	}
	
	@ExceptionHandler(SearchTypeInvalidException.class)
	public ResponseEntity<ErrorResponse> handleSearchTypeInvalidException(SearchTypeInvalidException e) {
		ErrorResponse response = new ErrorResponse(e.getErrorCode());
		return new ResponseEntity<>(response, HttpStatus.valueOf(e.getErrorCode().getStatus()));
	}
}
