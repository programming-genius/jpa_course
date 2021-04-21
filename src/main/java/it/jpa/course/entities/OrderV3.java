package it.jpa.course.entities;


import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "orders")
public class OrderV3 {

	@Id
	@Access(AccessType.FIELD)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence_generator")
	@SequenceGenerator(name = "order_sequence_generator", sequenceName = "order_sequence", initialValue = 1, allocationSize = 3)
	@Column(name = "id")
	private Long id;
	private Date orderDate;
	private boolean discount;
	private boolean completed;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "order_date")
	@Temporal(TemporalType.DATE)
	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Transient
	public boolean isDiscount() {
		return discount;
	}

	public void setDiscount(boolean discount) {
		this.discount = discount;
	}

	@Column(name = "completed")
	public boolean getCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

}
