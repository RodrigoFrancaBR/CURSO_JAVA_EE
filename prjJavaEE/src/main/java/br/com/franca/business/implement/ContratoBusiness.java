package br.com.franca.business.implement;

import br.com.franca.business.interfaces.ContratoBusinessI;
import br.com.franca.dao.implement.ContratoDAOImp;
import br.com.franca.domain.Contrato;

public class ContratoBusiness extends BusinessGeneric<Contrato, Long, ContratoDAOImp>
		implements ContratoBusinessI<Contrato, Long> {

	public ContratoBusiness() {
		super();
		this.dao = new ContratoDAOImp();
	}
}
