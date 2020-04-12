package br.com.franca.dao;

import javax.persistence.EntityManager;

import br.com.franca.domain.Unidade;

public class UnidadeDAO extends DAOGeneric<Unidade> {

	private static EntityManager em = EntityManagerUtil.getEntityManager();

	public UnidadeDAO() {
		super(Unidade.class, em);
	}

}
