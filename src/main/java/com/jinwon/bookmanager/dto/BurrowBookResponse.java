package com.jinwon.bookmanager.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BurrowBookResponse {
	private String status;
	private String message;
	
	@Builder
	public BurrowBookResponse(String status, String message) {
		this.status = status;
		this.message = message;
	}
}
