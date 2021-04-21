package it.jpa.course.beans;

import it.jpa.course.entities.InvoiceV3;
import it.jpa.course.entities.OrderV3;

public class InvoiceRequest {

	private OrderV3 order;
	private InvoiceV3 invoice;

	public OrderV3 getOrder() {
		return order;
	}

	public void setOrder(OrderV3 order) {
		this.order = order;
	}

	public InvoiceV3 getInvoice() {
		return invoice;
	}

	public void setInvoice(InvoiceV3 invoice) {
		this.invoice = invoice;
	}

}
