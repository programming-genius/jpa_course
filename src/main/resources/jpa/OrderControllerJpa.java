package it.jpa.course.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.jpa.course.entities.Order;
import it.jpa.course.exceptions.RollbackException;
import it.jpa.course.repositories.OrderRepositoryJpa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class OrderControllerJpa {
	
	@Autowired
	private OrderRepositoryJpa orderRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(OrderControllerJpa.class);

	@RequestMapping("/order/add")
	public String add() {
		logger.info("Hello from Logback");
		Order order = new Order();
		try {
			orderRepository.add(order);
		} catch (RollbackException e) {
			return "Errore inserimento ordine";
		}
		return "Ordine inserito";
	}
		
	@RequestMapping("/order/upd/{id}/{sent}")
	public String upd(@PathVariable Long id, @PathVariable String sent) {	
		try {
			Order order = new Order();
			order.setId(id);
			orderRepository.upd(order);
		} catch (RollbackException e) {
			return "Errore aggiornamento ordine";
		}
		return "Ordine aggiornato";
	}
		
	@RequestMapping("/order/del/{id}")
	public String del(@PathVariable Long id) {	
		try {
			Order order = new Order();
			order.setId(id);
			orderRepository.del(order);
		} catch (RollbackException e) {
			return "Errore cancellazione ordine";
		}
		return "Ordine cancellato";
	}		
}
