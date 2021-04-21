package it.jpa.course.beans;

import java.util.List;

import it.jpa.course.entities.Registration;

public class Registrations {
	private List<Registration> registrations;

	public List<Registration> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(List<Registration> registrations) {
		this.registrations = registrations;
	}
}
