package it.jpa.course.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import it.jpa.course.entities.BookV4;
import it.jpa.course.exceptions.RollbackException;

@Component
public class BookRepositoryImpl implements BookRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(rollbackFor = { RollbackException.class })
	public void add(BookV4 book) throws RollbackException {
		em.persist(book);
	}

	@Override
	@Transactional(rollbackFor = { RollbackException.class })
	public void del(BookV4 book) throws RollbackException {
		BookV4 bookToDelete = em.find(BookV4.class, book.getId());
		if (bookToDelete != null) {
			em.remove(bookToDelete);
		} else {
			throw new RollbackException("Entità non esistente");
		}
	}

	@Override
	@Transactional(rollbackFor = { RollbackException.class })
	public void upd(BookV4 book) throws RollbackException {
		Session session = em.unwrap(Session.class);
		session.update(book);
	}
}
