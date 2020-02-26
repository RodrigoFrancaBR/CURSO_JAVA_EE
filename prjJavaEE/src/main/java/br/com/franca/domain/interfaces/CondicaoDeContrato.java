package br.com.franca.domain.interfaces;

import java.util.List;

import br.com.franca.domain.Contrato;
import br.com.franca.domain.Parcela;

public interface CondicaoDeContrato {
	
	public List<Parcela> calculaParcelas(Contrato contrato);

}
