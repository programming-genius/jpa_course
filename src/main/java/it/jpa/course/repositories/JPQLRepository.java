package it.jpa.course.repositories;

import java.util.List;

import it.jpa.course.beans.BooksCopyDTO;
import it.jpa.course.beans.BooksDTO;
import it.jpa.course.entities.BookV2;
import it.jpa.course.entities.BookV4;

public interface JPQLRepository {

	public List<BookV2> getSoldBooks();
	public List<BookV4> getBooksByAuthor(String author);
	public Double getRevenue();
	public List<String> getAuthors();
	public BooksCopyDTO getBooksCopy(String bookName);
	public List<BooksDTO> getBooks(int pageSize, int pageStart);
}
