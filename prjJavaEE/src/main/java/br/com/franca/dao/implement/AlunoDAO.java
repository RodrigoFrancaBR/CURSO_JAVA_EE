package br.com.franca.dao.implement;

import javax.persistence.EntityManager;

import br.com.franca.dao.interfaces.AlunoDAOI;
import br.com.franca.domain.Aluno;

public class AlunoDAO extends DAOGeneric<Aluno> implements AlunoDAOI {

	private static EntityManager em;

	public AlunoDAO() {
		super(Aluno.class, em);
	}
}
