package br.com.franca.web.api;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.franca.domain.Unidade;
import br.com.franca.exceptions.CursoServiceException;
import br.com.franca.service.UnidadeService;

public class UnidadeController extends CommonController implements UnidadeAPI {

	private UnidadeService service;

	@Inject
	public UnidadeController(UnidadeService service) {
		this.service = service;
	}

	@Override
	public Response findAll() {
		try {
			List<Unidade> resposta = service.findAll();
			return Response.ok().entity(resposta).build();
		} catch (Exception ex) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
		}
	}

	@Override
	public Response findById(Long id) {
		try {
			Unidade resposta = service.findById(id);
			return Response.ok().entity(resposta).build();
		} catch (CursoServiceException ex) {
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
			return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
		} catch (Exception ex) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
		}
	}

	@Override
	public Response update(Long id, Unidade unidade) {
		try {
			service.update(id, unidade);
			return Response.ok().build();
		} catch (CursoServiceException ex) {
			return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
		} catch (Exception ex) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
		}
	}

	@Override
	public Response delete(Long id) {
		try {
			service.delete(id);
			return Response.ok().build();
		} catch (CursoServiceException ex) {
			return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
		} catch (Exception ex) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
		}
	}
}
