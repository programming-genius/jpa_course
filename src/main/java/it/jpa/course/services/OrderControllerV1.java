package it.jpa.course.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.jpa.course.beans.Books;
import it.jpa.course.entities.OrderV1;
import it.jpa.course.exceptions.RollbackException;
import it.jpa.course.repositories.OrderRepositoryV1;

@RestController
public class OrderControllerV1 {

	@Autowired
	private OrderRepositoryV1 orderRepository;

	private static final Logger logger = LoggerFactory.getLogger(OrderControllerV1.class);

	@PostMapping(value="/order/v1/add", consumes = "application/json", produces = "text/plain")
	public String add(@RequestBody Books books) {
		logger.info("[RegistrationController::add] Start");
		try {
			orderRepository.add(books.getBooks());
			logger.info("[OrderController::add] OK");
		} catch (RollbackException e) {
			logger.info("[OrderController::add] KO");
			return "Errore inserimento ordine";
		}
		return "Ordine inserito";
	}
	
	@DeleteMapping(value="/order/v1/del", consumes = "application/json", produces = "text/plain")
	public String del(@RequestBody OrderV1 order) {
		logger.info("[OrderController::del] Start");
		try {
			orderRepository.del(order.getId());
			logger.info("[OrderController::del] OK");
		} catch (RollbackException e) {
			logger.info("[OrderController::del] KO");
			return "Errore cancellazione ordine";
		}
		return "Ordine cancellato";
	}
	
	@PutMapping(value="/order/v1/upd", consumes = "application/json", produces = "text/plain")
	public String upd(@RequestBody Books books) {
		logger.info("[OrderController::upd] Start");
		try {
			orderRepository.upd(books.getBooks(), books.getId());
			logger.info("[OrderController::upd] OK");
		} catch (RollbackException e) {
			logger.info("[OrderController::upd] KO");
			return "Errore nell'aggiornamento dell'ordine";
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.info("[OrderController::upd] KO");
			return "Errore nell'aggiornamento dell'ordine";
		}
		return "Ordine aggiornato";
	}
}
