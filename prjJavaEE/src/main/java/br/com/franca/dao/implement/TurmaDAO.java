package br.com.franca.dao.implement;

import br.com.franca.dao.interfaces.TurmaDAOI;
import br.com.franca.domain.Turma;

public class TurmaDAO extends DAOGeneric<Turma, Long> implements TurmaDAOI {

	public TurmaDAO() {
		super();
		this.dominio =  Turma.class;
	}
}
