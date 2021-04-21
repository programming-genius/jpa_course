package it.jpa.course.entities;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "book")
public class BookV4 {
	
	@Id
	@Access(AccessType.FIELD)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_sequence_generator")
	@SequenceGenerator(name = "book_sequence_generator", sequenceName = "book_sequence", initialValue = 1, allocationSize = 3)
	@Column(name = "id")
	private Long id;
	private String name;
	private List<AuthorV4> authorV4;
	
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToMany
	@JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
	public List<AuthorV4> getAuthorV4() {
		return authorV4;
	}

	public void setAuthorV4(List<AuthorV4> authorV4) {
		this.authorV4 = authorV4;
	}
}
