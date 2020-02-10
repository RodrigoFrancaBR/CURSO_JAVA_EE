package br.com.franca.dao.implement;

import br.com.franca.crud.interfaces.CRUDI;
import br.com.franca.dao.interfaces.UnidadeDAOI;
import br.com.franca.domain.Unidade;

public class UnidadeDAOImp extends DAOGeneric<Unidade, Long>  implements CRUDI<Unidade,Long> {

	public UnidadeDAOImp() {
		super();
	}

}
