package br.com.franca.dao.implement;

import java.util.List;

import br.com.franca.dao.interfaces.ParcelaDAOI;
import br.com.franca.domain.Contrato;
import br.com.franca.domain.Parcela;

public class ParcelaDAO extends DAOGeneric<Parcela, Long> implements ParcelaDAOI {

	public ParcelaDAO() {
		super();
		this.dominio = Parcela.class;
	}

	@Override
	public List<Parcela> inserir(Contrato contrato) {
		// TODO Auto-generated method stub
		return null;
	}
}
