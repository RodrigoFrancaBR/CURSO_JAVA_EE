package br.com.franca.web.api.implement;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.franca.business.UnidadeBusiness;
import br.com.franca.business.exceptions.CursoServiceException;
import br.com.franca.domain.Unidade;
import br.com.franca.web.api.interfaces.UnidadeAPI;

@Path("unidades")
public class UnidadeResource extends ResourceGeneric<Unidade> implements UnidadeAPI {

	@Inject
	private UnidadeBusiness business;

	@Override
	public Response findAll() {

		try {
			return Response.ok(business.findAll()).build();
		} catch (CursoServiceException ex) {
			ex.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
		}
	}

	@Override
	public Response find(Long id) {
		Unidade resposta = null;

		try {
			resposta = this.business.find(id);
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
	public Response insert(Unidade unidade) {

		try {
			Unidade resposta = this.business.insert(unidade);
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
			return Response.ok(this.business.update(unidade)).build();
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
			this.business.delete(id);
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
