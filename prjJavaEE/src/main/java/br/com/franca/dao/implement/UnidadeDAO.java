package br.com.franca.dao.implement;

import javax.persistence.EntityManager;

import br.com.franca.dao.EntityManagerUtil;
import br.com.franca.dao.interfaces.UnidadeDAOI;
import br.com.franca.domain.Unidade;

public class UnidadeDAO extends DAOGeneric<Unidade> implements UnidadeDAOI {

	private static EntityManager em = EntityManagerUtil.getEntityManager();

	public UnidadeDAO() {
		super(Unidade.class, em);
	}

}
