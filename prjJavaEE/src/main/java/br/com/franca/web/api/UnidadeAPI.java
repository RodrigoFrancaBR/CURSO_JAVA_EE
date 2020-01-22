package br.com.franca.web.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.com.franca.domain.Unidade;

@Path("unidade")
public interface UnidadeAPI {
	// @GET
	@Path("/findAll")
	@Consumes("text/plain")
	// @Produces("{application/json}")
	@GET
	@Produces("text/plain")
	// public List<Unidade> findAll();
	public String findAll();
}
