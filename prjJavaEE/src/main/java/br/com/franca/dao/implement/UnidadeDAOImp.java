package br.com.franca.dao.implement;

import br.com.franca.dao.interfaces.UnidadeDAOI;
import br.com.franca.domain.Unidade;

public class UnidadeDAOImp extends DAOGeneric<Unidade, Long> implements UnidadeDAOI {

	public UnidadeDAOImp() {
		super();
		this.clazz = Unidade.class;
	}

}
