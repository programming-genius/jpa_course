package it.jpa.course.repositories;

import it.jpa.course.entities.BookV4;
import it.jpa.course.exceptions.RollbackException;

public interface BookRepository {

	public void add(BookV4 book) throws RollbackException;
	public void del(BookV4 book) throws RollbackException;
	public void upd(BookV4 book) throws RollbackException;
	
}
