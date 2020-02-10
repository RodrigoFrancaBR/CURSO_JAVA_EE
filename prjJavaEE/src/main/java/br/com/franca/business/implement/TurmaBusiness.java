package br.com.franca.business.implement;

import br.com.franca.business.interfaces.TurmaBusinessI;
import br.com.franca.dao.implement.TurmaDAOImp;
import br.com.franca.domain.Turma;

public class TurmaBusiness extends BusinessGeneric<Turma, Long, TurmaDAOImp> implements TurmaBusinessI<Turma, Long> {

	public TurmaBusiness() {
		super();
		this.dao = new TurmaDAOImp();
	}
}
