package br.com.franca.web.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class CustomNotFoundException extends WebApplicationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomNotFoundException() {

	}

	public CustomNotFoundException(String message) {
		 super(Response.status(Response.Status.NOT_FOUND).
				    entity(message).type("text/plain").build());
		// super(Response.status(Response.Status.UNAUTHORIZED).entity(message).type(MediaType.TEXT_PLAIN).build());
	}
}
