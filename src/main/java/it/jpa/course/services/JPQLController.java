package it.jpa.course.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import it.jpa.course.beans.BooksCopyDTO;
import it.jpa.course.beans.BooksDTO;
import it.jpa.course.entities.BookV2;
import it.jpa.course.entities.BookV4;
import it.jpa.course.repositories.JPQLRepository;

@RestController
public class JPQLController {

	@Autowired
	private JPQLRepository jpqlRepository;

	private static final Logger logger = LoggerFactory.getLogger(JPQLController.class);

	@GetMapping(value = "/jpql/soldBooks", produces = "application/json")
	public List<BookV2> getSoldBooks() {
		logger.info("[JPQLController::getSoldBooks] Start");
		List<BookV2> soldBooks = jpqlRepository.getSoldBooks();
		List<BookV2> soldBooksRes = new ArrayList<BookV2>();
		List<BookV2> empty = new ArrayList<BookV2>();
		for (BookV2 book : soldBooks) {
			book.getOrderV2().setBooksV2(empty);
			soldBooksRes.add(book);
		}
		logger.info("[JPQLController::getSoldBooks] OK");
		return soldBooksRes;
	}

	@GetMapping(value = "/jpql/booksByAuthors/{author}", produces = "application/json")
	public List<BookV4> getBooksByAuthors(@PathVariable String author) {
		logger.info("[JPQLController::getBooksByAuthors] Start");
		List<BookV4> booksByAuthors = jpqlRepository.getBooksByAuthor(author);
		logger.info("[JPQLController::getBooksByAuthors] OK");
		return booksByAuthors;
	}
	
	@GetMapping(value = "/jpql/revenue", produces = "application/json")
	public String getRevenue() {
		logger.info("[JPQLController::getRevenue] Start");
		Double revenue = jpqlRepository.getRevenue();
		logger.info("[JPQLController::getRevenue] OK");
		return (revenue==null?"Nessun risultato":revenue.toString());
	}
	
	@GetMapping(value = "/jpql/authors", produces = "application/json")
	public List<String> getAuthors() {
		logger.info("[JPQLController::getAuthors] Start");
		List<String> authors = jpqlRepository.getAuthors();
		logger.info("[JPQLController::getAuthors] OK");
		return authors;
	}
	
	@GetMapping(value = "/jpql/booksCopy/{bookName}", produces = "application/json")
	public BooksCopyDTO getBooksCopy(@PathVariable String bookName) {
		logger.info("[JPQLController::getBooksCopy] Start");
		BooksCopyDTO booksCopyDTO = jpqlRepository.getBooksCopy(bookName);
		logger.info("[JPQLController::getBooksCopy] OK");
		return booksCopyDTO;
	}
	
	@GetMapping(value = "/jpql/books/{pageStart}/{pageSize}", produces = "application/json")
	public List<BooksDTO> getBooks(@PathVariable int pageStart, @PathVariable int pageSize) {
		logger.info("[JPQLController::getBooksCopy] Start");
		List<BooksDTO> booksDTO = jpqlRepository.getBooks(pageStart, pageSize);
		logger.info("[JPQLController::getBooksCopy] OK");
		return booksDTO;
	}
	
}
