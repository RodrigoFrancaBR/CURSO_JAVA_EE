package br.com.franca.business.exceptions;

public class CursoServiceException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1350345618621303525L;

	public CursoServiceException() {
		// TODO Auto-generated constructor stub
	}

	public CursoServiceException(String message) {
		super(message);
	}

	public CursoServiceException(Throwable e) {
		super(e);
	}
}
