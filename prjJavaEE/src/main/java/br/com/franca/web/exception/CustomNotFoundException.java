package br.com.franca.web.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.xml.ws.soap.AddressingFeature.Responses;

public class CustomNotFoundException extends WebApplicationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create a HTTP 404 (Not Found) exception.
	 */
	public CustomNotFoundException() {
		// super(Responses.notFound().build());
	}

	/**
	 * Create a HTTP 404 (Not Found) exception.
	 * 
	 * @param message the String that is the entity of the 404 response.
	 */
	public CustomNotFoundException(String message) {
		// super(Response.status(Responses.NOT_FOUND).entity(message).type("text/plain").build());
	}
}
