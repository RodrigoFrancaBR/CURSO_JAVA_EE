package br.com.franca.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.franca.domain.Turma;
import br.com.franca.repository.TurmaRepository;

public class TurmaDAO extends DAOGeneric<Turma> implements TurmaRepository {

	private static EntityManager em = EntityManagerUtil.getEntityManager();

	public TurmaDAO() {
		super(Turma.class, em);
	}

	@Override
	public List<Turma> findAllTurmasByUnidadeId(Long unidadeId) {
		// TODO Auto-generated method stub
		return null;
	}
}
