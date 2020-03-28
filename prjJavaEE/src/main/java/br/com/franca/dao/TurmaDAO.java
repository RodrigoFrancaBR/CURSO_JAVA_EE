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
		// SELECT COUNT(*) FROM TB_UNIDADE AS U INNER JOIN TB_TURMA AS T ON U.ID=T.UNIDADE_ID AND U.ID = 1;
		// SELECT U.NOME AS NOME_UNIDADE FROM TB_UNIDADE as U INNER JOIN TB_TURMA AS T ON U.ID = T.UNIDADE_ID AND T.ID=2;
		String jpql="select t.unidade.nome from Turma t";
		List<String> resultList = em.createQuery(jpql, String.class).getResultList();
		for (String string : resultList) {
			System.out.println(string);
		}
		return null;
	}
}
