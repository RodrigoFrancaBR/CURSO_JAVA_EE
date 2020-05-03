package br.com.franca.repository;

import java.util.List;

import br.com.franca.domain.Contrato;
import br.com.franca.domain.Parcela;
import br.com.franca.exceptions.CursoDAOException;

public interface ContratoRepository {
	Contrato save(Contrato contrato, List<Parcela> listaDeParcelas) throws CursoDAOException;
}
