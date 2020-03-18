package br.com.franca.web.api.implement;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.franca.business.AlunoBusiness;
import br.com.franca.business.exceptions.CursoServiceException;
import br.com.franca.domain.Aluno;
import br.com.franca.web.api.interfaces.AlunoAPI;

@Path("alunos")

public class AlunoResource extends ResourceGeneric<Aluno> implements AlunoAPI {

	private AlunoBusiness business = new AlunoBusiness();

	@Override
	public Response findAll() {
		List<Aluno> resposta = null;
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
		Aluno resposta = null;
		try {

			resposta = this.business.find(id);

			if (domainIsNull(resposta)) {
				return Response.status(Status.NOT_FOUND).entity(resposta).build();
			}

			return Response.status(Status.OK).entity(resposta).build();

		} catch (CursoServiceException ex) {
			ex.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(resposta).build();
		}
	}

	@Override
	public Response insert(Aluno aluno) {
		Aluno resposta = null;

		try {

			resposta = this.business.insert(aluno);

		} catch (CursoServiceException ex) {
			ex.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(resposta).build();
		}

		try {

			URI uri = new URI(getUri("alunos/") + resposta.getId());

			return Response.created(uri).entity(resposta).type(MediaType.APPLICATION_JSON_TYPE).build();

		} catch (URISyntaxException ex) {
			ex.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(resposta).build();
		}
	}

	@Override
	public Response update(Aluno aluno) {

		Aluno resposta = null;

		try {

			return Response.ok(this.business.update(aluno)).build();

		} catch (CursoServiceException ex) {
			ex.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(resposta).build();
		}
	}

	@Override
	public Response delete(Long id) {

		Aluno resposta = null;

		try {
			resposta = this.business.delete(id);
			return Response.ok(resposta).build();

		} catch (CursoServiceException ex) {
			ex.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(resposta).build();
		}
	}

}
