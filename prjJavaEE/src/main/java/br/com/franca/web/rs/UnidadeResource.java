package br.com.franca.web.rs;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
	public List<Unidade> findAll() {
		return this.business.findAll();
	}

	@Override
	public Response find(Long id) {
		Unidade resposta = this.business.find(id);

		if (domainIsNull(resposta)) {
			// throw new WebApplicationException(404);
			 throw new CustomNotFoundException("Item, " + resposta + ", is not found");
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
		return null;

	}

	@Override
	public Unidade update(Unidade unidade) {
		return this.business.update(unidade);
	}

	@Override
	public Unidade delete(Long id) {
		return this.business.delete(id);
	}

}
