package it.jpa.course.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import it.jpa.course.entities.InvoiceV3;
import it.jpa.course.entities.OrderV3;
import it.jpa.course.exceptions.RollbackException;

@Component
public class InvoiceRepositoryImpl implements InvoiceRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(rollbackFor = { RollbackException.class })
	public void add(InvoiceV3 invoice, Long orderId) throws RollbackException {
		if (orderId == null || orderId == 0)
			throw new RollbackException("Entità non valida");
		OrderV3 order = em.find(OrderV3.class, orderId);
		invoice.setOrderV3(order);
		em.persist(invoice);
	}

	@Override
	@Transactional(rollbackFor = { RollbackException.class })
	public void del(InvoiceV3 invoice) throws RollbackException {
		if (invoice.getId() == null || invoice.getId() == 0)
			throw new RollbackException("Entità non valida");
		InvoiceV3 invoiceToDelete = em.find(InvoiceV3.class, invoice.getId());
		if (invoiceToDelete != null) {
			em.remove(invoiceToDelete);
		} else {
			throw new RollbackException("Entità non esistente");
		}
	}

	@Override
	@Transactional(rollbackFor = { RollbackException.class })
	public void upd(InvoiceV3 invoice) throws RollbackException, RuntimeException {
		if (invoice.getId() == null || invoice.getId() == 0)
			throw new RollbackException("Entità non valida");
		Session session = em.unwrap(Session.class);
		session.update(invoice);
	}
}
