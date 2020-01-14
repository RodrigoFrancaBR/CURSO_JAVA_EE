package br.com.franca.web.rs;

import javax.ws.rs.Path;

import br.com.franca.web.api.HelloWorldAPI;

@Path("helloworld")
public class HelloWorldResource implements HelloWorldAPI {
	public static final String CLICHED_MESSAGE = "Hello World33!";

	public String getHello() {
		return CLICHED_MESSAGE;
	}
}
