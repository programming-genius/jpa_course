package it.jpa.course.entities;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "book")
public class BookV1 {
	
	@Id
	@Access(AccessType.FIELD)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_sequence_generator")
	@SequenceGenerator(name = "book_sequence_generator", sequenceName = "book_sequence", initialValue = 1, allocationSize = 3)
	@Column(name = "id")
	private Long id;
	private String name;
	private OrderV1 orderV1;
	
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="order_id")
	public OrderV1 getOrderV1() {
		return orderV1;
	}

	public void setOrderV1(OrderV1 orderV1) {
		this.orderV1 = orderV1;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
}
