package br.com.franca.web.api.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.franca.domain.Parcela;

@Path("parcelas")
public interface ParcelaAPI {
	@GET
	// @Path("/findAll")
	// @Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll();

	@GET
	@Path("/{id}/")
	// @Path("/find/{id}/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response find(@PathParam("id") Long id);

	@POST
	// @Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insert(Parcela parcela);

	@PUT
	// @Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Parcela parcela);

	@DELETE
	@Path("/{id}/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") Long id);

	/*
	 * @POST // @Path("/findAll")
	 * 
	 * @Path("/simular")
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON)
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public Response
	 * simularParcelas(Contrato contrato);
	 */
	/*
	 * @POST // @Path("/findAll")
	 * 
	 * @Path("/")
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON)
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public Response inserir(Contrato
	 * contrato);
	 */
}
