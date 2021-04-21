package it.jpa.course.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.jpa.course.entities.Registration;
import it.jpa.course.exceptions.RollbackException;

@Component
public class RegistrationRepositoryImpl implements RegistrationRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(rollbackFor = { RollbackException.class })
	public void add(Registration registration) throws RollbackException {
		em.persist(registration);
	}

	@Override
	@Transactional(rollbackFor = { RollbackException.class })
	public void del(Registration registration) throws RollbackException {
		if (registration.getId() == null || registration.getId() == 0)
			throw new RollbackException("Entità non valida");
		em.remove(em.merge(registration));
	}

	@Override
	@Transactional(rollbackFor = { RollbackException.class })
	public void upd(Registration registration) throws RollbackException, RuntimeException {
		if (registration.getId() == null || registration.getId() == 0)
			throw new RollbackException("Entità non valida");
		em.merge(registration);
	}

	@Override
	@Transactional(rollbackFor = { RollbackException.class })
	public void addAll(List<Registration> registrations) throws RollbackException {
		for (Registration registration : registrations) {
			em.persist(registration);
		}
	}
	
	@Override
	@Transactional
	public void concurrency1(long id) throws InterruptedException {
		Registration registration = em.find(Registration.class,id);
		registration.setName("Optimistic lock1");
		registration.setLastname("Optimistic lock1");
		Thread.sleep(5000);
	}
	
	@Override
	@Transactional
	public void concurrency2(long id) {
		Registration registration = em.find(Registration.class, 1L);
		registration.setName("Optimistic lock2");
		registration.setLastname("Optimistic lock2");
	}
	
	@Override
	@Transactional
	public void concurrency1(Registration registration) throws InterruptedException {
		Session session = em.unwrap(Session.class);
		session.update(registration);
		Thread.sleep(5000);
	}
	
	@Override
	@Transactional
	public void concurrency2(Registration registration)  {
		Session session = em.unwrap(Session.class);
		session.update(registration);
	}
}
