package com.jinwon.bookmanager.dto;

import javax.validation.constraints.NotBlank;
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
public class UpdateBookRequest {
	@NotNull(message = "도서 정보 업데이트에 필요한 도서 ID값은 필수입니다.")
	@ApiParam(value = "도서 ID", required = true)
	private Long bookId;
	@NotBlank(message = "도서 정보 업데이트에 필요한 도서 카테고리값은 필수입니다.")
	@ApiParam(value = "도서 카테고리", required = true)
	private String bookCategory;
}
