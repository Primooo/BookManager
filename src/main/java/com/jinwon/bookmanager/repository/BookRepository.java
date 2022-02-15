package com.jinwon.bookmanager.repository;

import com.jinwon.bookmanager.entity.BookEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, String>{
	List<BookEntity> findAllByBookCategory(String bookCategory);
	List<BookEntity> findAllByBookTitleContaining(String bookTitle);
	List<BookEntity> findAllByBookWriter(String bookWriter);
	BookEntity findByBookId(Long id);
}
