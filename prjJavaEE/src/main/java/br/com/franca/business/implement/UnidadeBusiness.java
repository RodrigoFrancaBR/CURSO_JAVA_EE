package br.com.franca.business.implement;

import br.com.franca.business.interfaces.UnidadeBusinessI;
import br.com.franca.dao.implement.UnidadeDAOImp;
import br.com.franca.domain.Unidade;

public class UnidadeBusiness extends BusinessGeneric<Unidade, Long, UnidadeDAOImp> implements UnidadeBusinessI<Unidade, Long> {

	public UnidadeBusiness() {
		super();
		this.dao = new UnidadeDAOImp();
	}
}
