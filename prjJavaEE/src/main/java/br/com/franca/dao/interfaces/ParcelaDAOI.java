package br.com.franca.dao.interfaces;

import java.util.List;

import br.com.franca.domain.Contrato;
import br.com.franca.domain.Parcela;

public interface ParcelaDAOI extends CRUDI<Parcela, Long> {
	List<Parcela> inserir(Contrato contrato);
}
