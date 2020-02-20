package br.com.franca.web.rs;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.franca.business.UnidadeBusiness;
import br.com.franca.domain.Unidade;
import br.com.franca.web.api.UnidadeAPI;
import br.com.franca.web.exception.CustomNotFoundException;

@Path("unidades")
public class UnidadeResource extends ResourceGeneric<Unidade> implements UnidadeAPI {

	private UnidadeBusiness business;

	public UnidadeResource() {
		this.business = new UnidadeBusiness();
	}

	@Override
	public Response findAll() {
		return Response.ok(business.findAll()).build();
	}

	@Override
	public Response find(Long id) {
		Unidade resposta = this.business.find(id);

		if (domainIsNull(resposta)) {
			// throw new WebApplicationException(404);
			throw new CustomNotFoundException("Unidade, " + resposta + ", is not found");
		}

		return Response.ok(resposta).build();
	}

	@Override
	public Response insert(Unidade unidade) {
		Unidade resposta = this.business.insert(unidade);

		try {
			return Response.created(new URI(getUri("unidades/") + resposta.getId())).entity(resposta)
					.type(MediaType.APPLICATION_JSON_TYPE).build();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		// return null;
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Não foi possível inserir a unidade").build();

	}

	@Override
	public Response update(Unidade unidade) {
		return Response.ok(this.business.update(unidade)).build();
	}

	@Override
	public Response delete(Long id) {
		Unidade resposta = this.business.delete(id);
		return domainIsNull(resposta)
				? Response.status(Status.INTERNAL_SERVER_ERROR).entity("Não foi possível remover a unidade").build()
				: Response.ok(resposta).build();
	}

}
