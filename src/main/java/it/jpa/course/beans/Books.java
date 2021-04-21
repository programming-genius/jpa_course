package it.jpa.course.beans;

import java.util.List;

import it.jpa.course.entities.BookV1;

public class Books {
	private List<BookV1> books;
	private Long id;

	public List<BookV1> getBooks() {
		return books;
	}

	public void setBooks(List<BookV1> books) {
		this.books = books;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
