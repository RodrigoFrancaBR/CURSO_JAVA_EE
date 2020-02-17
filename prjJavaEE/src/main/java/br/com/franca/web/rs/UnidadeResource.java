package br.com.franca.web.rs;

import java.util.List;

import javax.ws.rs.Path;

import br.com.franca.business.UnidadeBusiness;
import br.com.franca.domain.Unidade;
import br.com.franca.web.api.UnidadeAPI;

@Path("unidades")
public class UnidadeResource implements UnidadeAPI {

	private UnidadeBusiness business;

	public UnidadeResource() {
		this.business = new UnidadeBusiness();
	}

	@Override
	public List<Unidade> findAll() {
		return this.business.findAll();
	}

	@Override
	public Unidade find(Long id) {
		return this.business.find(id);
	}

	@Override
	public Unidade insert(Unidade unidade) {
		return this.business.insert(unidade);
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
