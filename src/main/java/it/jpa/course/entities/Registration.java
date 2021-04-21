package it.jpa.course.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "registration")
public class Registration {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="registration_sequence_generator")
	@GenericGenerator(name ="registration_sequence_generator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator",
	parameters={
	@Parameter(name="sequence_name", value="registration_sequence"),
	@Parameter(name="initial_value", value="1"),
	@Parameter(name="increment_size", value="3"),
	@Parameter(name="optimizer", value="pooled-lo"),
	})
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;
	@Column(name = "lastname")
	private String lastname;
	
	/*@Version
	private int version;*/

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

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/*public int getVersion() {
		return version;
	}*/

	/*public void setVersion(int version) {
		this.version = version;
	}*/
}
