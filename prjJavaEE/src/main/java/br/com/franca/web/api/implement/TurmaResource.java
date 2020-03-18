package br.com.franca.web.api.implement;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

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

	private TurmaBusiness business = new TurmaBusiness();

	@Override
	public Response findAll() {
		List<Turma> resposta = null;
		try {

			resposta = business.findAll();

			return Response.ok(resposta).build();

		} catch (CursoServiceException ex) {
			ex.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(resposta).build();
		}
	}

	@Override
	public Response find(Long id) {
		Turma resposta = null;
		try {

			resposta = this.business.find(id);

			if (domainIsNull(resposta)) {
				// throw new WebApplicationException(404);
				// throw new CustomNotFoundException("Turma, " + resposta + ", is not found");
				return Response.status(Status.NOT_FOUND).entity(resposta).build();
			}

			// return Response.ok(resposta).build();
			return Response.status(Status.OK).entity(resposta).build();

		} catch (CursoServiceException ex) {
			ex.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(resposta).build();
		}
	}

	@Override
	public Response insert(Turma turma) {
		Turma resposta = null;

		try {

			resposta = this.business.insert(turma);

		} catch (CursoServiceException ex) {
			ex.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(resposta).build();
		}

		try {

			URI uri = new URI(getUri("turmas/") + resposta.getId());

			return Response.created(uri).entity(resposta).type(MediaType.APPLICATION_JSON_TYPE).build();

		} catch (URISyntaxException ex) {
			ex.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(resposta).build();
		}
		// return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Não foi possível
		// inserir a turma").build();
	}

	@Override
	public Response update(Turma turma) {

		Turma resposta = null;

		try {

			// resposta = this.business.update(turma);

			return Response.ok(this.business.update(turma)).build();

		} catch (CursoServiceException ex) {
			ex.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(resposta).build();
		}
	}

	@Override
	public Response delete(Long id) {

		Turma resposta = null;

		try {
			resposta = this.business.delete(id);
			return Response.ok(resposta).build();

		} catch (CursoServiceException ex) {
			ex.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(resposta).build();
		}
		/*
		 * return domainIsNull(resposta) ?
		 * Response.status(Status.INTERNAL_SERVER_ERROR).
		 * entity("Não foi possível remover a turma").build() :
		 * Response.ok(resposta).build();
		 */
	}

}
