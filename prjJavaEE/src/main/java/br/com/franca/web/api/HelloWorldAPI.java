/**
 * 
 */
package br.com.franca.web.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @author Rodrigo
 *
 */
@Path("helloworld")
public interface HelloWorldAPI {

	@GET
	@Produces("text/plain")
	String getHello();
}
