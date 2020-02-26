package br.com.franca.dao.implement;

import br.com.franca.dao.interfaces.AlunoDAOI;
import br.com.franca.domain.Aluno;

public class AlunoDAO extends DAOGeneric<Aluno, Long> implements AlunoDAOI {

	public AlunoDAO() {
		super();
		this.dominio = Aluno.class;
	}
}
