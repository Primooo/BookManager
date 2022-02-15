package com.jinwon.bookmanager.exception_handler.exceptions;

import com.jinwon.bookmanager.exception_handler.code.ErrorCode;

import lombok.Getter;

@Getter
public class BookNotFoundException extends RuntimeException{
	private ErrorCode errorCode;
	
	public BookNotFoundException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
