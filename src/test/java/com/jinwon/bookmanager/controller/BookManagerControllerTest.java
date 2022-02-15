package com.jinwon.bookmanager.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jinwon.bookmanager.dto.BurrowBookRequest;
import com.jinwon.bookmanager.dto.CreateBookRequest;
import com.jinwon.bookmanager.dto.UpdateBookRequest;
import com.jinwon.bookmanager.service.BookService;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookManagerControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private BookService bookService;

	@Test
	public void getBooklistTest() throws Exception {
		objectMapper = new ObjectMapper();

		mockMvc.perform(get("/api/book")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());
	}

	@Test
	public void searchBookTest() throws Exception {
		objectMapper = new ObjectMapper();

		mockMvc.perform(get("/api/book/search")
						.param("type", "category")
						.param("keyword", "IT")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());
	}

	@Test
	public void createFailTest() throws Exception {
		objectMapper = new ObjectMapper();
		CreateBookRequest createBookRequest = new CreateBookRequest("테스트 책 제목", "", "서진원");
		String content = objectMapper.writeValueAsString(createBookRequest);

		mockMvc.perform(post("/api/book/create")
						.content(content)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$.message").value("도서 카테고리는 필수 요청 사항입니다."))
				.andDo(print());
	}

	@Test
	public void createSuccessTest() throws Exception {
		objectMapper = new ObjectMapper();
		CreateBookRequest createBookRequest = new CreateBookRequest("테스트 책 제목", "IT", "서진원");
		String content = objectMapper.writeValueAsString(createBookRequest);

		mockMvc.perform(post("/api/book/create")
						.content(content)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());
	}

	@Test
	public void updateFailTest() throws Exception {
		objectMapper = new ObjectMapper();
		UpdateBookRequest updateBookRequest = new UpdateBookRequest(null, "IT");
		String content = objectMapper.writeValueAsString(updateBookRequest);

		mockMvc.perform(put("/api/book/update")
						.content(content)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$.message").value("수정에 필요한 책 ID 정보는 필수 요청 사항입니다."))
				.andDo(print());
	}

	@Test
	public void updateSuccessTest() throws Exception {
		objectMapper = new ObjectMapper();
		UpdateBookRequest updateBookRequest = new UpdateBookRequest((long) 5, "IT");
		String content = objectMapper.writeValueAsString(updateBookRequest);

		mockMvc.perform(put("/api/book/update")
						.content(content)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());
	}

	@Test
	public void burrowSuccessTest() throws Exception {
		objectMapper = new ObjectMapper();
		BurrowBookRequest burrowBookRequest = new BurrowBookRequest((long)5);
		String content = objectMapper.writeValueAsString(burrowBookRequest);

		mockMvc.perform(post("/api/book/burrow")
						.content(content)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());
	}

	@Test
	public void burrowFailTest() throws Exception {
		objectMapper = new ObjectMapper();
		BurrowBookRequest burrowBookRequest = new BurrowBookRequest(null);
		String content = objectMapper.writeValueAsString(burrowBookRequest);

		mockMvc.perform(post("/api/book/burrow")
						.content(content)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$.message").value("대여하고자 하는 책 Id 정보는 필수 요청사항입니다."))
				.andDo(print());
	}
}
