package it.jpa.course.entities;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "invoice")
public class InvoiceV3 {
	@Id
	@Access(AccessType.FIELD)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoice_sequence_generator")
	@SequenceGenerator(name = "invoice_sequence_generator", sequenceName = "invoice_sequence", initialValue = 1, allocationSize = 3)
	@Column(name = "id")
	private Long id;
	private float price;
	private OrderV3 orderV3;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "price")
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@OneToOne
	@JoinColumn(name = "order_id")
	public OrderV3 getOrderV3() {
		return orderV3;
	}

	public void setOrderV3(OrderV3 orderV3) {
		this.orderV3 = orderV3;
	}
}
