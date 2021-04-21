package it.jpa.course.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.jpa.course.entities.BookV2;
import it.jpa.course.entities.OrderV2;
import it.jpa.course.exceptions.RollbackException;

@Component
public class OrderRepositoryImplV2 implements OrderRepositoryV2 {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(rollbackFor = { RollbackException.class })
	public void add(OrderV2 order) throws RollbackException {
		List<BookV2> attachedBooks = new ArrayList<BookV2>();
		for (BookV2 book : order.getBooksV2()) {
			BookV2 bookAttached = em.find(BookV2.class, book.getId());
			if (bookAttached != null && bookAttached.getOrderV2() != null)
				throw new RollbackException("Libro non acquistabile");
			else {
				attachedBooks.add(bookAttached);
			}
		}
		em.persist(order);
		for (BookV2 book : attachedBooks) {
			order.addBook(book);
		}
	}

	@Override
	@Transactional(rollbackFor = { RollbackException.class })
	public void del(OrderV2 order) throws RollbackException {
		if (order.getId() == null || order.getId() == 0)
			throw new RollbackException("Entità non valida");
		OrderV2 orderToDelete = em.find(OrderV2.class, order.getId());
		if (orderToDelete == null) 
			throw new RollbackException("Ordine non esistente");
		List<BookV2> books = orderToDelete.getBooksV2();
		List<BookV2> booksCopy = new ArrayList<BookV2>();

		for (BookV2 book : books) {
			booksCopy.add(book);
		}

		for (BookV2 book : booksCopy) {
			orderToDelete.removeBook(book);
		}
		em.remove(orderToDelete);
	}
	

	@Override
	@Transactional(rollbackFor = { RollbackException.class })
	public void upd(Long id, List<BookV2> books) throws RollbackException {
		if (id == null || id == 0)
			throw new RollbackException("Entità non valida");
		OrderV2 orderToUpdate = em.find(OrderV2.class, id);

		if (orderToUpdate == null)
			throw new RollbackException("Errore sull'ordine");

		List<BookV2> oldBooks = orderToUpdate.getBooksV2();
		List<BookV2> oldBooksCopy = new ArrayList<BookV2>();

		for (BookV2 book : oldBooks) {
			oldBooksCopy.add(book);
		}

		for (BookV2 book : oldBooksCopy) {
			orderToUpdate.removeBook(book);
		}

		for (BookV2 book : books) {
			BookV2 bookAttached = em.find(BookV2.class, book.getId());
			if (bookAttached != null && bookAttached.getOrderV2() != null)
				throw new RollbackException("Libro non acquistabile");
			orderToUpdate.addBook(bookAttached);
		}
	}
}
