package it.jpa.course.entities;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
public class BookV2 {
	
	@Id
	@Access(AccessType.FIELD)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_sequence_generator")
	@SequenceGenerator(name = "book_sequence_generator", sequenceName = "book_sequence", initialValue = 1, allocationSize = 3)
	@Column(name = "id")
	private Long id;
	private String name;
	private OrderV2 orderV2;
	
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne
	@JoinColumn(name="order_id")
	public OrderV2 getOrderV2() {
		return orderV2;
	}

	public void setOrderV2(OrderV2 orderV2) {
		this.orderV2 = orderV2;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
