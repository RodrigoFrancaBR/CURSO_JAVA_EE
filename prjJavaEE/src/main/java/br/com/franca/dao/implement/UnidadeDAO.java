package br.com.franca.dao.implement;

import br.com.franca.dao.interfaces.CRUDI;
import br.com.franca.dao.interfaces.UnidadeDAOI;
import br.com.franca.domain.Unidade;

public class UnidadeDAO extends DAOGeneric<Unidade, Long>  implements CRUDI<Unidade,Long> {

	public UnidadeDAO() {
		super();
		this.dominio = Unidade.class;		
	}

}
