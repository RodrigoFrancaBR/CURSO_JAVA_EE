package br.com.franca.dao.implement;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.franca.dao.interfaces.ParcelaDAOI;
import br.com.franca.domain.Contrato;
import br.com.franca.domain.Parcela;

public class ParcelaDAO extends DAOGeneric<Parcela> implements ParcelaDAOI {

	private static EntityManager em;

	public ParcelaDAO() {
		super(Parcela.class, em);
	}

	@Override
	public List<Parcela> inserir(Contrato contrato) {
		// TODO Auto-generated method stub
		return null;
	}
}
