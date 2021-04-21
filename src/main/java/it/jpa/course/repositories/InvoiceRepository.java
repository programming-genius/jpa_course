package it.jpa.course.repositories;

import it.jpa.course.entities.InvoiceV3;
import it.jpa.course.exceptions.RollbackException;

public interface InvoiceRepository {

	public void add(InvoiceV3 invoice, Long orderId) throws RollbackException;
	public void del(InvoiceV3 invoice) throws RollbackException;
	public void upd(InvoiceV3 invoice) throws RollbackException;
	
}
