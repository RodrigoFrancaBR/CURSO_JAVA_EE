package br.com.franca.web.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.franca.domain.Contrato;

@Path("contratos")
public interface ContratoAPI {

	@GET
	// @Path("/findAll")
	// @Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Contrato> findAll();

	@GET
	@Path("/{id}/")
	// @Path("/find/{id}/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Contrato find(@PathParam("id") Long id);

	@POST
	// @Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Contrato insert(Contrato contrato);

	@PUT
	// @Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Contrato update(Contrato contrato);

	@DELETE
	@Path("/{id}/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Contrato delete(@PathParam("id") Long id);

}
