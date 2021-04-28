package it.jpa.course.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
		// Costruzione insieme lista id di libri
		List<String> ids = new ArrayList<>();
		for (BookV2 book : order.getBooksV2()) {
			ids.add(book.getId().toString());
		}
		String idsString = ids.stream().collect(Collectors.joining(",", "(", ")"));
		// Recupero libri dell'ordine in stato attached
		TypedQuery<BookV2> query = em
				.createQuery("select b from BookV2 b where b.orderV2 is null and b.id in " + idsString, BookV2.class);
		List<BookV2> books = query.getResultList();
		if (books.isEmpty())
			throw new RollbackException("Ordine vuoto");
		em.persist(order);
		// Aggiunta dei libri all'ordine sfruttando i metodi di sincronizzazione
		for (BookV2 book : books) {
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
		List<BookV2> booksToDelete = orderToDelete.getBooksV2();
		List<BookV2> booksToDeleteCopy = new ArrayList<BookV2>();
		booksToDeleteCopy.addAll(booksToDelete);
		// Con una copia della lista riesco a cancellare i libri dall'ordine
		// rispettando la sincronizzazione
		for (BookV2 book : booksToDeleteCopy) {
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

		for (BookV2 book : orderToUpdate.getBooksV2()) {
			orderToUpdate.removeBook(book);
		}

		List<String> ids = new ArrayList<>();
		for (BookV2 book : books) {
			ids.add(book.getId().toString());
		}
		String idsString = ids.stream().collect(Collectors.joining(",", "(", ")"));
		// Recupero libri dell'ordine in stato attached
		TypedQuery<BookV2> query = em
				.createQuery("select b from BookV2 b where b.orderV2 is null and b.id in " + idsString, BookV2.class);
		List<BookV2> attachedBooks = query.getResultList();
		for (BookV2 book : attachedBooks) {
			orderToUpdate.addBook(book);
		}
	}
}
