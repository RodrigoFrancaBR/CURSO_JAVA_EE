package br.com.franca.repository;

import java.util.List;

import br.com.franca.domain.Contrato;
import br.com.franca.domain.Parcela;

public interface ParcelaRepository {
	public List<Parcela> inserir(Contrato contrato);
}
