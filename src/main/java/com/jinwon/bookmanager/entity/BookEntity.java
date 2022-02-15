package com.jinwon.bookmanager.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Getter
@Setter
@Entity
@Table(name = "Book")
@NoArgsConstructor
public class BookEntity {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(value="도서 ID")
	private Long bookId;
	
	@Column(length=100)
	@ApiModelProperty(value="도서 제목")
    private String bookTitle;
    @Column(length=50)
	@ApiModelProperty(value="도서 카테고리")
    private String bookCategory;
    @Column(length=30)
	@ApiModelProperty(value="도서 저자")
    private String bookWriter;
    @Column(length=10)
	@ApiModelProperty(value="도서 대여 상태")
    private String bookStatus;

    @Builder
    public BookEntity(Long bookId, String bookTitle, String bookCategory, String bookWriter, String bookStatus) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookCategory = bookCategory;
        this.bookWriter = bookWriter;
        this.bookStatus = bookStatus;
    }
}
