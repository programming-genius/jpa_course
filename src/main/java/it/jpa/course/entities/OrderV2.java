package it.jpa.course.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "orders")
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class OrderV2 {

	@Id
	@Access(AccessType.FIELD)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence_generator")
	@SequenceGenerator(name = "order_sequence_generator", sequenceName = "order_sequence", initialValue = 1, allocationSize = 3)
	@Column(name = "id")
	private Long id;
	private Date orderDate;
	private boolean discount;
	private boolean completed;
	private List<BookV2> booksV2;

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

	@OneToMany(mappedBy = "orderV2", orphanRemoval = false)
	//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	public List<BookV2> getBooksV2() {
		return booksV2;
	}

	public void setBooksV2(List<BookV2> booksV2) {
		this.booksV2 = booksV2;
	}

	public void addBook(BookV2 book) {
		if(booksV2==null) {
			booksV2 = new ArrayList<BookV2>();
		}
		booksV2.add(book);
		book.setOrderV2(this);
	}
	
	public void removeBook(BookV2 bookV2) {
		booksV2.remove(bookV2);
		bookV2.setOrderV2(null);
	}
}
