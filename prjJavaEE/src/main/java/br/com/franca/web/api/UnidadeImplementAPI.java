package br.com.franca.web.api;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.franca.domain.Unidade;
import br.com.franca.service.UnidadeService;
import br.com.franca.service.exceptions.CursoServiceException;

public class UnidadeImplementAPI extends WebAPIGeneric<Unidade> implements UnidadeInterfaceAPI {

	private UnidadeService service;

	@Inject
	public UnidadeImplementAPI(UnidadeService service) {
		this.service = service;
	}

	@Override
	public Response findAll() {

		try {
			return Response.ok(service.findAll()).build();
		} catch (CursoServiceException ex) {
			ex.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
		}
	}

	@Override
	public Response findById(Long id) {

		try {
			
			Unidade resposta = service.findById(id);
			
			if (domainIsNull(resposta)) {
				return Response.status(Status.NOT_FOUND).entity(id).build();
				// throw new WebApplicationException(404);
				// throw new CustomNotFoundException("Unidade, " + resposta + ", is not found");
			}
			
			return Response.status(Status.OK).entity(resposta).build();
			// return Response.ok(resposta).build();
			
		} catch (CursoServiceException ex) {
			ex.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
		}
	}

	@Override
	public Response save(Unidade unidade) {

		try {
			Unidade resposta = service.save(unidade);
			URI uri = new URI(getUri("unidades/") + resposta.getId());
			return Response.created(uri).entity(resposta).type(MediaType.APPLICATION_JSON_TYPE).build();
		} catch (CursoServiceException ex) {
			ex.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
		}
	}

	@Override
	public Response update(Unidade unidade) {

		try {
			return Response.ok(service.update(unidade)).build();
		} catch (CursoServiceException ex) {
			ex.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
		}
	}

	@Override
	public Response delete(Long id) {

		try {
			service.delete(id);
			return Response.ok().build();
		} catch (CursoServiceException ex) {
			ex.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
		}
	}
}
