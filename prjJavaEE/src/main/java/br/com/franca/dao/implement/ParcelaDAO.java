package br.com.franca.dao.implement;

import br.com.franca.dao.interfaces.ParcelaDAOI;
import br.com.franca.domain.Parcela;

public class ParcelaDAO extends DAOGeneric<Parcela, Long> implements ParcelaDAOI {

	public ParcelaDAO() {
		super();
	}
}
