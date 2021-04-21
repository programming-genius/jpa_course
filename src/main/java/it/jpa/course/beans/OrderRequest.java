package it.jpa.course.beans;

import java.util.List;

import it.jpa.course.entities.BookV2;
import it.jpa.course.entities.OrderV2;

public class OrderRequest {

	private OrderV2 order;
	private List<BookV2> books;

	public OrderV2 getOrder() {
		return order;
	}

	public void setOrder(OrderV2 order) {
		this.order = order;
	}

	public List<BookV2> getBooks() {
		return books;
	}

	public void setBooks(List<BookV2> books) {
		this.books = books;
	}

}
