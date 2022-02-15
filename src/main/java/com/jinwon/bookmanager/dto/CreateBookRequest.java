package com.jinwon.bookmanager.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookRequest {
	@NotBlank(message = "도서 생성에 필요한 도서 제목값은 필수입니다.")
	@ApiParam(value = "도서 제목", required = true)
	private String bookTitle;
	@NotBlank(message = "도서 생성에 필요한 도서 카테고리값은 필수입니다.")
	@ApiParam(value = "도서 카테고리", required = true)
	private String bookCategory;
	@NotBlank(message = "도서 생성에 필요한 도서 저자값은 필수입니다.")
	@ApiParam(value = "도서 저자", required = true)
	private String bookWriter;
}
