package com.jinwon.bookmanager.controller;

import lombok.RequiredArgsConstructor;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import com.jinwon.bookmanager.dto.Book;
import com.jinwon.bookmanager.dto.BurrowBookRequest;
import com.jinwon.bookmanager.dto.BurrowBookResponse;
import com.jinwon.bookmanager.dto.CreateBookRequest;
import com.jinwon.bookmanager.dto.UpdateBookRequest;
import com.jinwon.bookmanager.service.BookService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;

@Api(value = "BookController", tags = {"도서 관리 API"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/book")
public class BookController {
	private final BookService bookService;

	@Operation(summary = "등록된 모든 도서 조회 API")
	@ApiResponses({
			@ApiResponse(code = 200, message = "API 정상 작동 - 모든 도서 정보 리스트를 응답합니다."),
			@ApiResponse(code = 500, message = "서버 에러")
	})
	@GetMapping()
	public ResponseEntity<List<Book>> getBooks() {
		return new ResponseEntity<>(bookService.getBooks(), HttpStatus.OK);
	}

	@Operation(summary = "도서 검색 API")
	@ApiResponses({
			@ApiResponse(code = 200, message = "API 정상 작동 - 검색된 도서 정보 리스트를 응답합니다."),
			@ApiResponse(code = 400, message = "검색 Type 정보 에러"),
			@ApiResponse(code = 500, message = "서버 에러")
	})
	@GetMapping("/search")
	public ResponseEntity<List<Book>> getSearchedBooks(
			@ApiParam(value = "검색 타입(카테고리/제목/저자)", required = true) @RequestParam String type,
			@ApiParam(value = "검색어") @RequestParam(required = false) String keyword) {
		return new ResponseEntity<>(bookService.getSearchedBooks(type, keyword), HttpStatus.OK);
	}

	@Operation(summary = "도서 등록 API")
	@ApiResponses({
			@ApiResponse(code = 200, message = "API 정상 작동 - 등록한 도서 정보를 응답합니다."),
			@ApiResponse(code = 400, message = "도서 등록 정보 에러"),
			@ApiResponse(code = 500, message = "서버 에러")
	})
	@PostMapping("/create")
	public ResponseEntity<Book> createBook(@RequestBody @Valid CreateBookRequest request) {
		return new ResponseEntity<>(bookService.createBook(request), HttpStatus.OK);
	}

	@Operation(summary = "도서 카테고리 정보 수정 API")
	@ApiResponses({
			@ApiResponse(code = 200, message = "API 정상 작동 - 수정한 도서 정보를 응답합니다."),
			@ApiResponse(code = 400, message = "도서 카테고리 수정에 필요한 정보 에러"),
			@ApiResponse(code = 500, message = "서버 에러")
	})
	@PutMapping("/update")
	public ResponseEntity<Book> updateBookCategory(@RequestBody @Valid UpdateBookRequest request) {
		return new ResponseEntity<>(bookService.updateCategory(request), HttpStatus.OK);
	}

	@Operation(summary = "도서 대여 API")
	@ApiResponses({
			@ApiResponse(code = 200, message = "API 정상 작동 - 도서 대여 결과와 성공 혹은 실패 메시지를 응답합니다"),
			@ApiResponse(code = 400, message = "도서 대여에 필요한 ID 정보 Null 에러"),
			@ApiResponse(code = 500, message = "서버 에러")
	})
	@PostMapping("/burrow")
	public ResponseEntity<BurrowBookResponse> burrowBook(@RequestBody @Valid BurrowBookRequest request) {
		return new ResponseEntity<>(bookService.burrowBook(request), HttpStatus.OK);
	}
	
}
