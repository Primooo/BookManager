package com.jinwon.bookmanager.exception_handler.exceptions;

import com.jinwon.bookmanager.exception_handler.code.ErrorCode;

import lombok.Getter;

@Getter
public class SearchTypeInvalidException extends RuntimeException {
	private ErrorCode errorCode;
	
	public SearchTypeInvalidException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
