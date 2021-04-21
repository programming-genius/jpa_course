package it.jpa.course.beans;

public class BooksCopyDTO {
	private String name;
	private long copy;

	public BooksCopyDTO(String name, long copy) {
		this.name = name;
		this.copy = copy;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCopy() {
		return copy;
	}

	public void setCopy(long copy) {
		this.copy = copy;
	}

}
