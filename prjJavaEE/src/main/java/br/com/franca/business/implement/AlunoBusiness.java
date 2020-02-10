package br.com.franca.business.implement;

import br.com.franca.business.interfaces.AlunoBusinessI;
import br.com.franca.dao.implement.AlunoDAOImp;
import br.com.franca.domain.Aluno;

public class AlunoBusiness extends BusinessGeneric<Aluno, Long, AlunoDAOImp> implements AlunoBusinessI<Aluno, Long> {

	public AlunoBusiness() {
		super();
		this.dao = new AlunoDAOImp();
	}
}
