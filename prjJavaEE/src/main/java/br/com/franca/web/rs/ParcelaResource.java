package br.com.franca.web.rs;

import java.util.List;

import javax.ws.rs.Path;

import br.com.franca.business.ParcelaBusiness;
import br.com.franca.domain.Parcela;
import br.com.franca.web.api.ParcelaAPI;

@Path("parcelas")
public class ParcelaResource implements ParcelaAPI {

	private ParcelaBusiness business;

	public ParcelaResource() {
		super();
		this.business = new ParcelaBusiness();
	}

	@Override
	public List<Parcela> findAll() {
		// this.business.findAll();
		return null;
	}

	@Override
	public Parcela find(Long id) {
		return null;
	}

	@Override
	public Parcela insert(Parcela entity) {
		return null;
	}

	@Override
	public Parcela update(Parcela entity) {
		return null;
	}

	@Override
	public Parcela delete(Long id) {
		return null;
	}

}
