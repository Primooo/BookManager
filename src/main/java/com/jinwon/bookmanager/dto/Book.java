package com.jinwon.bookmanager.dto;

import com.jinwon.bookmanager.entity.BookEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Book {
	private Long bookId;
	private String bookTitle;
	private String bookCategory;
	private String bookWriter;
	private String bookStatus;
	
	public BookEntity toEntity() {
        return BookEntity.builder()
                .bookTitle(bookTitle)
                .bookCategory(bookCategory)
                .bookWriter(bookWriter)
                .bookStatus(bookStatus)
                .build();
    }
	
    public Book(BookEntity bookEntity) {
        this.bookId = bookEntity.getBookId();
        this.bookTitle = bookEntity.getBookTitle();
        this.bookCategory = bookEntity.getBookCategory();
        this.bookWriter = bookEntity.getBookWriter();
        this.bookStatus = bookEntity.getBookStatus();
    }
    
    public Book(CreateBookRequest bookRequest) {
    	this.bookTitle = bookRequest.getBookTitle();
    	this.bookWriter = bookRequest.getBookWriter();
    	this.bookCategory = bookRequest.getBookCategory();
    }

    @Builder
    public Book(Long bookId, String bookTitle, String bookCategory, String bookWriter, String bookStatus) {
    	this.bookId = bookId;
    	this.bookTitle = bookTitle;
    	this.bookCategory = bookCategory;
    	this.bookWriter = bookWriter;
    	this.bookStatus = bookStatus;
    }
}
