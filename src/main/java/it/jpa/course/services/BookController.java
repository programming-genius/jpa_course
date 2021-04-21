package it.jpa.course.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.jpa.course.beans.BookRequest;
import it.jpa.course.entities.BookV4;
import it.jpa.course.exceptions.RollbackException;
import it.jpa.course.repositories.BookRepository;

@RestController
public class BookController {

	@Autowired
	private BookRepository bookRepository;

	private static final Logger logger = LoggerFactory.getLogger(BookController.class);

	@PostMapping(value="/book/v4/add", consumes = "application/json", produces = "text/plain")
	public String add(@RequestBody BookRequest bookRequest) {
		logger.info("[BookController::add] Start");
		try {
			BookV4 book = bookRequest.getBook();
			book.setAuthorV4(bookRequest.getAuthor());
			bookRepository.add(book);
			logger.info("[BookController::add] OK");
		} catch (RollbackException e) {
			logger.info("[BookController::add] KO");
			return "Errore inserimento libro";
		}
		return "Libro inserito";
	}

	@PutMapping(value="/book/v4/upd", consumes = "application/json", produces = "text/plain")
	public String upd(@RequestBody BookRequest bookRequest) {
		logger.info("[BookController::upd] Start");
		try {
			BookV4 book = bookRequest.getBook();
			book.setAuthorV4(bookRequest.getAuthor());
			bookRepository.upd(book);
			logger.info("[BookController::upd] OK");
		} catch (RollbackException e) {
			logger.info("[BookController::upd] KO");
			return "Errore nell'aggiornamento del libro";
		} catch (RuntimeException e) {
			logger.info("[BookController::upd] KO");
			return "Errore nell'aggiornamento del libro";
		}
		return "Libro aggiornato";
	}

	@DeleteMapping(value="/book/v4/del", consumes = "application/json", produces = "text/plain")
	public String del(@RequestBody BookRequest bookRequest) {
		logger.info("[BookController::del] Start");
		try {
			bookRepository.del(bookRequest.getBook());
			logger.info("[BookController::del] OK");
		} catch (RollbackException e) {
			logger.info("[BookController::del] KO");
			return "Errore canellazione libro";
		}
		return "Libro cancellato";
	}
}
