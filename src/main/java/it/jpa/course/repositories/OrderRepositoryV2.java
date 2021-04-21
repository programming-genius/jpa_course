package it.jpa.course.repositories;

import java.util.List;

import it.jpa.course.entities.BookV2;
import it.jpa.course.entities.OrderV2;
import it.jpa.course.exceptions.RollbackException;

public interface OrderRepositoryV2 {

	public void add(OrderV2 order) throws RollbackException;
	public void del(OrderV2 order) throws RollbackException;
	public void upd(Long id, List<BookV2> books) throws RollbackException;
	
}
