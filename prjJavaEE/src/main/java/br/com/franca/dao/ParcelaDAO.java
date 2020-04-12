package br.com.franca.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.franca.domain.Contrato;
import br.com.franca.domain.Parcela;
import br.com.franca.repository.ParcelaRepository;

public class ParcelaDAO extends DAOGeneric<Parcela> implements ParcelaRepository {

	private static EntityManager em = EntityManagerUtil.getEntityManager();

	public ParcelaDAO() {
		super(Parcela.class, em);
	}

	@Override
	public List<Parcela> inserir(Contrato contrato) {
		// TODO Auto-generated method stub
		return null;
	}
}
