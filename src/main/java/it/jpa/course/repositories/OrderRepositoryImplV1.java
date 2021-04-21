package it.jpa.course.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
		Query query = em.createQuery("select b from BookV1 b join fetch b.orderV1 o where o.id=" + id);
		@SuppressWarnings("unchecked")
		List<BookV1> books = query.getResultList();
		Session session = em.unwrap(Session.class);
		for (BookV1 book : books) {
			book.setOrderV1(null);
			session.update(book);
		}
		OrderV1 order = em.find(OrderV1.class, id);
		em.remove(order);
	}

	@Override
	@Transactional(rollbackFor = { RollbackException.class })
	public void upd(List<BookV1> books, Long id) throws RollbackException {
		Query query = em.createQuery("select b from BookV1 b join fetch b.orderV1 o where o.id=" + id);
		@SuppressWarnings("unchecked")
		List<BookV1> oldBooks = query.getResultList();
		if (oldBooks == null || oldBooks.size() == 0)
			throw new RollbackException("Errore aggiornamento ordine");
		OrderV1 order = oldBooks.get(0).getOrderV1();
		for (BookV1 book : oldBooks) {
			book.setOrderV1(null);
		}
		Session session = em.unwrap(Session.class);
		for (BookV1 book : books) {
			BookV1 bookAttached = em.find(BookV1.class, book.getId());
			if (bookAttached != null && bookAttached.getOrderV1() != null)
				throw new RollbackException("Libro non acquistabile");
			book.setOrderV1(order);
			session.update(book);
		}
	}
}
