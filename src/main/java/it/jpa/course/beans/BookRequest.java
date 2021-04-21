package it.jpa.course.beans;

import java.util.List;

import it.jpa.course.entities.AuthorV4;
import it.jpa.course.entities.BookV4;

public class BookRequest {

	private BookV4 book;
	private List<AuthorV4> author;
	
	public BookV4 getBook() {
		return book;
	}
	public void setBook(BookV4 book) {
		this.book = book;
	}
	public List<AuthorV4> getAuthor() {
		return author;
	}
	public void setAuthor(List<AuthorV4> author) {
		this.author = author;
	}
	
}
