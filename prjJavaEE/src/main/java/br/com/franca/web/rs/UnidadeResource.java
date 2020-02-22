package br.com.franca.web.rs;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.franca.business.UnidadeBusiness;
import br.com.franca.domain.Unidade;
import br.com.franca.web.api.UnidadeAPI;
import br.com.franca.web.exception.CursoServiceException;

@Path("unidades")
public class UnidadeResource extends ResourceGeneric<Unidade> implements UnidadeAPI {

	private UnidadeBusiness business;

	public UnidadeResource() {
		this.business = new UnidadeBusiness();
	}

	@Override
	public Response findAll() {
		List<Unidade> resposta = null;
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
		Unidade resposta = null;
		try {

			resposta = this.business.find(id);

			if (domainIsNull(resposta)) {
				// throw new WebApplicationException(404);
				// throw new CustomNotFoundException("Unidade, " + resposta + ", is not found");
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
	public Response insert(Unidade unidade) {
		Unidade resposta = null;

		try {

			resposta = this.business.insert(unidade);

		} catch (CursoServiceException ex) {
			ex.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(resposta).build();
		}

		try {

			URI uri = new URI(getUri("unidades/") + resposta.getId());

			return Response.created(uri).entity(resposta).type(MediaType.APPLICATION_JSON_TYPE).build();

		} catch (URISyntaxException ex) {
			ex.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(resposta).build();
		}
		// return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Não foi possível
		// inserir a unidade").build();
	}

	@Override
	public Response update(Unidade unidade) {

		Unidade resposta = null;

		try {

			// resposta = this.business.update(unidade);

			return Response.ok(this.business.update(unidade)).build();

		} catch (CursoServiceException ex) {
			ex.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(resposta).build();
		}
	}

	@Override
	public Response delete(Long id) {

		Unidade resposta = null;

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
		 * entity("Não foi possível remover a unidade").build() :
		 * Response.ok(resposta).build();
		 */
	}

}
