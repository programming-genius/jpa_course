package it.jpa.course.services;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.jpa.course.beans.OrderRequest;
import it.jpa.course.entities.BookV2;
import it.jpa.course.entities.OrderV2;
import it.jpa.course.exceptions.RollbackException;
import it.jpa.course.repositories.OrderRepositoryV2;

@RestController
public class OrderControllerV2 {

	@Autowired
	private OrderRepositoryV2 orderRepository;

	private static final Logger logger = LoggerFactory.getLogger(OrderControllerV2.class);

	@PostMapping(value="/order/v2/add", consumes = "application/json", produces = "text/plain")
	public String add(@RequestBody OrderRequest orderRequest) {
		logger.info("[RegistrationController::add] Start");
		try {
			OrderV2 order = orderRequest.getOrder();
			order.setOrderDate(new Date());
			for (BookV2 book: orderRequest.getBooks()) {
				order.addBook(book);
			}
			orderRepository.add(order);
			logger.info("[OrderController::add] OK");
		} catch (RollbackException e) {
			logger.info("[OrderController::add] KO");
			return "Errore inserimento ordine";
		}
		return "Ordine inserito";
	}
	
	@DeleteMapping(value="/order/v2/del", consumes = "application/json", produces = "text/plain")
	public String del(@RequestBody OrderRequest orderRequest) {
		logger.info("[OrderController::del] Start");
		try {
			OrderV2 order = orderRequest.getOrder();
			orderRepository.del(order);
			logger.info("[OrderController::del] OK");
		} catch (RollbackException e) {
			logger.info("[OrderController::del] KO");
			return "Errore cancellazione ordine";
		}
		return "Ordine cancellato";
	}
	
	@PutMapping(value="/order/v2/upd", consumes = "application/json", produces = "text/plain")
	public String upd(@RequestBody OrderRequest orderRequest) {
		logger.info("[OrderController::upd] Start");
		try {
			orderRepository.upd(orderRequest.getOrder().getId(), orderRequest.getBooks());
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
