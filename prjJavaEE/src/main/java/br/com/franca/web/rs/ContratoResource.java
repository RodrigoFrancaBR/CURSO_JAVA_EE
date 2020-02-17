package br.com.franca.web.rs;

import java.util.List;

import javax.ws.rs.Path;

import br.com.franca.business.ContratoBusiness;
import br.com.franca.domain.Contrato;
import br.com.franca.web.api.ContratoAPI;

@Path("contratos")
public class ContratoResource implements ContratoAPI {

	private ContratoBusiness business;

	public ContratoResource() {
		super();
		// this.business = new ContratoBusiness();
	}

	@Override
	public List<Contrato> findAll() {
		// this.business.findAll();
		return null;
	}

	@Override
	public Contrato find(Long id) {
		return null;
	}

	@Override
	public Contrato insert(Contrato entity) {
		return null;
	}

	@Override
	public Contrato update(Contrato entity) {
		return null;
	}

	@Override
	public Contrato delete(Long id) {
		return null;
	}

}
