package br.com.franca.business.implement;

import br.com.franca.business.interfaces.ParcelaBusinessI;
import br.com.franca.dao.implement.ParcelaDAOImp;
import br.com.franca.domain.Parcela;

public class ParcelaBusiness extends BusinessGeneric<Parcela, Long, ParcelaDAOImp>
		implements ParcelaBusinessI<Parcela, Long> {

	public ParcelaBusiness() {
		super();
		this.dao = new ParcelaDAOImp();
	}
	
}
