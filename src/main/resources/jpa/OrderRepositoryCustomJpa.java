package it.jpa.course.repositories;

import it.jpa.course.entities.Order;
import it.jpa.course.exceptions.RollbackException;

public interface OrderRepositoryCustomJpa {
	
	public void add(Order order) throws RollbackException;
	public void del(Order order) throws RollbackException;
	public void upd(Order order) throws RollbackException;
	
}
