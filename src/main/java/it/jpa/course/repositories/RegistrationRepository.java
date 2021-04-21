package it.jpa.course.repositories;

import java.util.List;

import it.jpa.course.entities.Registration;
import it.jpa.course.exceptions.RollbackException;

public interface RegistrationRepository {

	public void add(Registration order) throws RollbackException;
	public void del(Registration order) throws RollbackException;
	public void upd(Registration order) throws RollbackException;
	public void addAll(List<Registration> registrations) throws RollbackException;
	public void concurrency1(long id) throws InterruptedException;
	public void concurrency2(long id);
	public void concurrency1(Registration registration) throws InterruptedException;
	public void concurrency2(Registration registration);
}
