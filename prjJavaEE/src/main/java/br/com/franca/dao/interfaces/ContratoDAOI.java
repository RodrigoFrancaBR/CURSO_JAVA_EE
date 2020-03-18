package br.com.franca.dao.interfaces;

import java.util.List;

import br.com.franca.dao.exceptions.CursoDAOException;
import br.com.franca.domain.Contrato;
import br.com.franca.domain.Parcela;

public interface ContratoDAOI {
	Contrato save(Contrato contrato, List<Parcela> listaDeParcelas) throws CursoDAOException;

	List<Contrato> findAll() throws CursoDAOException;

	Contrato find(Long id) throws CursoDAOException;

	Contrato update(Contrato contrato) throws CursoDAOException;

	Contrato delete(Contrato contrato) throws CursoDAOException;
}
