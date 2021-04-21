package it.jpa.course.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String name;
	
	@ManyToOne
	@JoinColumn(name="order_id")
	private OrderV1 order;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public OrderV1 getOrder() {
		return order;
	}

	public void setOrder(OrderV1 order) {
		this.order = order;
	}

}
