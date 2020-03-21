package br.com.franca.dao.implement;

import javax.persistence.EntityManager;

import br.com.franca.dao.EntityManagerUtil;
import br.com.franca.dao.interfaces.TurmaDAOI;
import br.com.franca.domain.Turma;

public class TurmaDAO extends DAOGeneric<Turma> implements TurmaDAOI {

	private static EntityManager em = EntityManagerUtil.getEntityManager();

	public TurmaDAO() {
		super(Turma.class, em);		
	}
}
