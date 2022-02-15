package com.jinwon.bookmanager.service;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jinwon.bookmanager.dto.Book;
import com.jinwon.bookmanager.dto.BurrowBookRequest;
import com.jinwon.bookmanager.dto.BurrowBookResponse;
import com.jinwon.bookmanager.dto.CreateBookRequest;
import com.jinwon.bookmanager.dto.UpdateBookRequest;
import com.jinwon.bookmanager.entity.BookEntity;
import com.jinwon.bookmanager.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
public class BookManagerServiceTest {
	@Mock
	private BookRepository bookRepository;

	@InjectMocks
	private BookService bookService;

	@Test
	@DisplayName("전체 Book list 조회 테스트")
	public void getBookListTest() throws Exception {
		//given
		BookEntity testBook1 = new BookEntity((long)1, "테스트 이름1", "IT", "서진원", "대여가능");
		BookEntity testBook2 = new BookEntity((long)2, "테스트 이름2", "인문학", "서진원", "훼손");
		doReturn(Arrays.asList(
				testBook1,
				testBook2
		)).when(bookRepository).findAll();

		//when
		List<Book> searchedBookList = bookService.getBooks();

		// then
		Assertions.assertEquals(searchedBookList.size(), 2);
		Assertions.assertEquals(searchedBookList.get(0).getBookTitle(), "테스트 이름1");
	}

	@Test
	@DisplayName("도서 검색 테스트")
	public void getSearchedBookListTest() throws Exception {
		//given
		final String type = "category";
		final String keyword = "IT";
		BookEntity testBook1 = new BookEntity((long)1, "테스트 이름1", "IT", "서진원", "대여가능");
		doReturn(Arrays.asList(
				testBook1
		)).when(bookRepository).findAllByBookCategory(keyword);

		//when
		List<Book> searchedBookList = bookService.getSearchedBooks(type, keyword);

		// then
		Assertions.assertEquals(searchedBookList.size(), 1);
		Assertions.assertEquals(searchedBookList.get(0).getBookTitle(), "테스트 이름1");
	}

	@Test
	@DisplayName("도서 등록 테스트")
	public void createBookTest() throws Exception {
		//given
		Book testBook1 = new Book((long)1, "테스트 이름1", "인문학", "서진원", "대여가능");
		CreateBookRequest testBookRequest = new CreateBookRequest("테스트 이름1", "인문학", "서진원");
		given(bookRepository.save(any())).willReturn(testBook1.toEntity());

		//when
		Book createdBook = bookService.createBook(testBookRequest);

		// then
		Assertions.assertEquals(createdBook.getBookTitle(), testBook1.getBookTitle());
		Assertions.assertEquals(createdBook.getBookCategory(), testBook1.getBookCategory());
		Assertions.assertEquals(createdBook.getBookWriter(), testBook1.getBookWriter());
	}

	@Test
	@DisplayName("도서 카테고리 수정 테스트")
	public void updateBookCategoryTest() throws Exception {
		//given
		BookEntity testBook1 = new BookEntity((long)1, "테스트 이름1", "인문학", "서진원", "대여가능");
		UpdateBookRequest testRequest = new UpdateBookRequest((long)1, "IT");
		doReturn(testBook1).when(bookRepository).findByBookId(testRequest.getBookId());

		//when
		Book updatedBook = bookService.updateCategory(testRequest);

		// then
		Assertions.assertEquals("IT", updatedBook.getBookCategory());
	}

	@Test
	@DisplayName("도서 대여 테스트")
	public void burrowBookTest() throws Exception {
		//given
		BookEntity testBook1 = new BookEntity((long)1, "테스트 이름1", "인문학", "서진원", "대여가능");
		BurrowBookRequest testRequest = new BurrowBookRequest((long)1);
		BurrowBookResponse testResponse = new BurrowBookResponse("대여성공", "성공적으로 대여 신청이 완료되었습니다.");
		doReturn(testBook1).when(bookRepository).findByBookId(testRequest.getBookId());

		//when
		BurrowBookResponse burrowResponse = bookService.burrowBook(testRequest);

		// then
		Assertions.assertEquals(burrowResponse.getMessage(), testResponse.getMessage());
		Assertions.assertEquals(burrowResponse.getStatus(), testResponse.getStatus());
	}
}
