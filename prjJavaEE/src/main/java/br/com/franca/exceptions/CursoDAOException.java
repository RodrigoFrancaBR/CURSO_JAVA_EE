package br.com.franca.exceptions;

public class CursoDAOException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4006053101689362657L;

	public CursoDAOException() {
	}

	public CursoDAOException(String message) {
		super(message);
	}

	public CursoDAOException(Throwable e) {
		super(e);
	}

	public CursoDAOException(String message, IllegalArgumentException illegalArgumentEx) {
		super(message, illegalArgumentEx);
	}

}