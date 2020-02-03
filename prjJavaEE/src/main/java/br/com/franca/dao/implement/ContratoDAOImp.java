package br.com.franca.dao.implement;

import br.com.franca.dao.interfaces.ContratoDAOI;
import br.com.franca.domain.Contrato;

public class ContratoDAOImp extends DAOGeneric<Contrato, Long> implements ContratoDAOI {
	public ContratoDAOImp() {
		super();
		this.clazz = Contrato.class;
	}
}
