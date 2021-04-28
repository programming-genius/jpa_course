package it.jpa.course.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.jpa.course.entities.BookV1;
import it.jpa.course.entities.OrderV1;
import it.jpa.course.exceptions.RollbackException;

@Component
public class OrderRepositoryImplV1 implements OrderRepositoryV1 {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(rollbackFor = { RollbackException.class })
	public void add(List<BookV1> books) throws RollbackException {
		OrderV1 order = new OrderV1();
		order.setOrderDate(new Date());
		em.persist(order);
		Session session = em.unwrap(Session.class);
		for (BookV1 book : books) {
			BookV1 bookAttached = em.find(BookV1.class, book.getId());
			if (bookAttached != null && bookAttached.getOrderV1() != null)
				throw new RollbackException("Libro non acquistabile");
			else
				em.detach(bookAttached);
			book.setOrderV1(order);
			session.update(book);
		}
	}

	@Override
	@Transactional(rollbackFor = { RollbackException.class })
	public void del(Long id) throws RollbackException {
		TypedQuery<BookV1> query = em.createQuery("select b from BookV1 b join fetch b.orderV1 o where o.id=" + id,
				BookV1.class);
		List<BookV1> books = query.getResultList();
		for (BookV1 book : books) {
			book.setOrderV1(null);
		}
		OrderV1 order = em.find(OrderV1.class, id);
		em.remove(order);
	}

	@Override
	@Transactional(rollbackFor = { RollbackException.class })
	public void upd(List<BookV1> books, Long id) throws RollbackException {
		// Recupero libri di un ordine
		TypedQuery<BookV1> query = em.createQuery("select b from BookV1 b join fetch b.orderV1 o where o.id=" + id,
				BookV1.class);
		List<BookV1> oldBooks = query.getResultList();
		if (oldBooks == null || oldBooks.size() == 0)
			throw new RollbackException("Errore aggiornamento ordine");
		// Tutti i libri referenziano lo stesso ordine
		OrderV1 order = oldBooks.get(0).getOrderV1();
		// Elimino l'associazione corrente
		for (BookV1 book : oldBooks) {
			book.setOrderV1(null);
		}
		// Aggiorno l'associazione con la nuova lista di libri
		Session session = em.unwrap(Session.class);
		for (BookV1 book : books) {
			// Book è detached attenzione
			book.setOrderV1(order);
			//em.merge(book);// Prova questo al posto di session e guarda il log
			// Con session vado in update sugli oggetti detached senza necessità di portarli
			// in stato attached con una query (merge)
			session.update(book);
		}
	}
}
