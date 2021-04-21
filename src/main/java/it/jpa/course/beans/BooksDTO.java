package it.jpa.course.beans;

public class BooksDTO {
	private String name;
	private long id;

	public BooksDTO(String name, long id) {
		this.name = name;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setCopy(long id) {
		this.id = id;
	}

}
