package br.com.franca.dao;

import javax.persistence.EntityManager;

import br.com.franca.domain.Aluno;

public class AlunoDAO extends DAOGeneric<Aluno> {

	private static EntityManager em = EntityManagerUtil.getEntityManager();

	public AlunoDAO() {
		super(Aluno.class, em);
	}
}
