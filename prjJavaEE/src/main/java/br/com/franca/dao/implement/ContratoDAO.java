package br.com.franca.dao.implement;

import br.com.franca.dao.interfaces.ContratoDAOI;
import br.com.franca.domain.Contrato;

public class ContratoDAO extends DAOGeneric<Contrato, Long> implements ContratoDAOI {

	public ContratoDAO() {
		super();
	}
}
