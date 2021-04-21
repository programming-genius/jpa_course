package it.jpa.course.entities;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "author")
public class AuthorV4 {
	@Id
	@Access(AccessType.FIELD)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_sequence_generator")
	@SequenceGenerator(name = "author_sequence_generator", sequenceName = "author_sequence", initialValue = 1, allocationSize = 3)
	@Column(name = "id")
	private Long id;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
