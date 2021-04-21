package it.jpa.course.exceptions;

public class RollbackException extends Exception {

	private static final long serialVersionUID = -7954649967319447114L;

	public RollbackException(String message) {
		super(message);
	}
}
