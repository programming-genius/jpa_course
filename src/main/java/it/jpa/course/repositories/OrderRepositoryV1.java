package it.jpa.course.repositories;

import java.util.List;

import it.jpa.course.entities.BookV1;
import it.jpa.course.exceptions.RollbackException;

public interface OrderRepositoryV1 {

	public void add(List<BookV1> books) throws RollbackException;
	public void del(Long id) throws RollbackException;
	public void upd(List<BookV1> books, Long id) throws RollbackException;
	
}
