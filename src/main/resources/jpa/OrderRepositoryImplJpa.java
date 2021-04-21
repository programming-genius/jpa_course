package it.jpa.course.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import it.jpa.course.entities.Order;
import it.jpa.course.exceptions.RollbackException;

public class OrderRepositoryImplJpa implements OrderRepositoryCustomJpa {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(rollbackFor = { RollbackException.class })
	public void add(Order order) throws RollbackException {
		em.persist(order);
	}

	@Override
	@Transactional(rollbackFor = { RollbackException.class })
	public void del(Order order) throws RollbackException {
		em.remove(em.merge(order));
		
	}

	@Override
	@Transactional(rollbackFor = { RollbackException.class })
	public void upd(Order order) throws RollbackException {
		em.merge(order);
	}
}
