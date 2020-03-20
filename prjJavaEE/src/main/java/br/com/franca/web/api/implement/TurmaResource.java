package br.com.franca.web.api.implement;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.franca.business.TurmaBusiness;
import br.com.franca.business.exceptions.CursoServiceException;
import br.com.franca.domain.Turma;
import br.com.franca.web.api.interfaces.TurmaAPI;

@Path("turmas")
public class TurmaResource extends ResourceGeneric<Turma> implements TurmaAPI {

	@Inject
	private TurmaBusiness business;

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
		Turma resposta = null;

		try {
			resposta = this.business.find(id);
			if (domainIsNull(resposta)) {
				return Response.status(Status.NOT_FOUND).entity(id).build();
			}
			return Response.status(Status.OK).entity(resposta).build();
		} catch (CursoServiceException ex) {
			ex.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
		}
	}

	@Override
	public Response insert(Turma turma) {
		
		try {
			Turma resposta = this.business.insert(turma);
			URI uri = new URI(getUri("turmas/") + resposta.getId());
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
	public Response update(Turma turma) {

		try {
			return Response.ok(this.business.update(turma)).build();
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
