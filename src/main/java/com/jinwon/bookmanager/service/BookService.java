package com.jinwon.bookmanager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jinwon.bookmanager.dto.Book;
import com.jinwon.bookmanager.dto.BurrowBookRequest;
import com.jinwon.bookmanager.dto.BurrowBookResponse;
import com.jinwon.bookmanager.dto.CreateBookRequest;
import com.jinwon.bookmanager.dto.UpdateBookRequest;
import com.jinwon.bookmanager.entity.BookEntity;
import com.jinwon.bookmanager.exception_handler.code.ErrorCode;
import com.jinwon.bookmanager.exception_handler.exceptions.BookNotFoundException;
import com.jinwon.bookmanager.exception_handler.exceptions.SearchTypeInvalidException;
import com.jinwon.bookmanager.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;
	
	public List<Book> getBooks() {
        List<BookEntity> bookList = bookRepository.findAll();

        return bookList.stream()
                .map(Book::new)
                .collect(Collectors.toList());
	}
	
	public List<Book> getSearchedBooks(String type, String keyword) {
		List<BookEntity> searchedBookList = null;
		
		if(type.equals("category")) {
			searchedBookList = bookRepository.findAllByBookCategory(keyword);
		} else if(type.equals("writer")) {
			searchedBookList = bookRepository.findAllByBookWriter(keyword);
		} else if(type.equals("title")) {
			searchedBookList = bookRepository.findAllByBookTitleContaining(keyword);
		} else {
			throw new SearchTypeInvalidException(ErrorCode.SEARCH_TYPE_INVALID);
		}
		
		return searchedBookList.stream()
				.map(Book::new)
				.collect(Collectors.toList());
	}
	
	public Book createBook(CreateBookRequest request) {
		Book newBook = new Book(request);
		newBook.setBookStatus("대여가능");
		bookRepository.save(newBook.toEntity());
		
		return newBook;
	}
	
	public Book updateCategory(UpdateBookRequest request) {
		BookEntity bookEntity = bookRepository.findByBookId(request.getBookId());
		if(bookEntity == null) {
			throw new BookNotFoundException(ErrorCode.NOT_FOUND_BOOK);
		}
		
		bookEntity.setBookCategory(request.getBookCategory());
		bookRepository.save(bookEntity);
		
		return Book.builder()
				.bookId(bookEntity.getBookId())
				.bookCategory(bookEntity.getBookCategory())
				.bookTitle(bookEntity.getBookTitle())
				.bookWriter(bookEntity.getBookWriter())
				.bookStatus(bookEntity.getBookStatus())
				.build();
	}
	
	public BurrowBookResponse burrowBook(BurrowBookRequest request) {
		BookEntity bookEntity = bookRepository.findByBookId(request.getBookId());
		if(bookEntity == null) {
			throw new BookNotFoundException(ErrorCode.NOT_FOUND_BOOK);
		}
		BurrowBookResponse response = new BurrowBookResponse();
		if(bookEntity.getBookStatus().equals("대여가능")) {
			response.setStatus("대여성공");
			response.setMessage("성공적으로 대여 신청이 완료되었습니다.");
		} else {
			response.setStatus("대여실패");
			response.setMessage("도서가 훼손 혹은 분실의 이유로 대여 신청이 되지 않습니다.");
		}
		return response;
	}
	
}
