package br.com.franca.dao.implement;

import br.com.franca.dao.interfaces.ParcelaDAOI;
import br.com.franca.domain.Parcela;

public class ParcelaDAOImp extends DAOGeneric<Parcela, Long> implements ParcelaDAOI {
	public ParcelaDAOImp() {
		super();
		this.clazz = Parcela.class;
	}
}
