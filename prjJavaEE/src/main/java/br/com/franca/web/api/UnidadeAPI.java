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

import br.com.franca.domain.Unidade;

@Path("unidade")
public interface UnidadeAPI {

	@GET
	@Path("/findAll")
	@Produces(MediaType.APPLICATION_JSON)
	// public List<Unidade> findAll();
	public List<Unidade> buscarTodas();

	@GET
	@Path("/find/{id}/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	// public Unidade find(@PathParam("id") Long id);
	public Unidade buscarPor(@PathParam("id") Long id);
	
	@POST
	@Path("/insert")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	// public Unidade insert(Unidade unidade);
	public Unidade inserir(Unidade unidade);

	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Unidade atualizar(Unidade unidade);
	// public Unidade update(Unidade unidade);

	@DELETE
	@Path("/delete/{id}/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	// public Unidade delete(@PathParam("id") Long id);
	public Unidade remover(@PathParam("id") Long id);
}
