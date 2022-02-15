package com.jinwon.bookmanager.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BurrowBookRequest {
	@NotNull(message = "도서 대여에 필요한 도서 ID값은 필수입니다.")
	@ApiParam(value = "도서 ID", type = "Long", required = true)
	private Long bookId;
}
