package br.com.franca.exceptions;

public class CursoServiceException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1350345618621303525L;

	public CursoServiceException() {
	}

	public CursoServiceException(String message) {
		super(message);
	}

	public CursoServiceException(Throwable e) {
		super(e);
	}
}
