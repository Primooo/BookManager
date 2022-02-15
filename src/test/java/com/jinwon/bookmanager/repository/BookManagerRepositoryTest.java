package com.jinwon.bookmanager.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.jinwon.bookmanager.dto.Book;
import com.jinwon.bookmanager.entity.BookEntity;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookManagerRepositoryTest {
	@Autowired
	private BookRepository bookRepository;

	@Test
	public void saveBookTest() {
		BookEntity bookEntity = new Book((long)1, "테스트용 책 제목", "IT", "서진원", "훼손").toEntity();

		BookEntity savedBookEntity = bookRepository.save(bookEntity);

		Assertions.assertEquals(bookEntity.getBookId(), savedBookEntity.getBookId());
		Assertions.assertEquals(bookEntity.getBookCategory(), savedBookEntity.getBookCategory());
		Assertions.assertEquals(bookEntity.getBookTitle(), savedBookEntity.getBookTitle());
		Assertions.assertEquals(bookEntity.getBookWriter(), savedBookEntity.getBookWriter());
		Assertions.assertEquals(bookEntity.getBookStatus(), savedBookEntity.getBookStatus());
	}
}
