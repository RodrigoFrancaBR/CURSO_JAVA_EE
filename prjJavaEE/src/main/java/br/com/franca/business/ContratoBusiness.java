package br.com.franca.business;

import java.util.ArrayList;
import java.util.List;

import br.com.franca.dao.implement.ContratoDAO;
import br.com.franca.domain.CondicaoDeContrato;
import br.com.franca.domain.Contrato;
import br.com.franca.domain.Parcela;
import br.com.franca.strategy.CursoMaterialAvista;
import br.com.franca.web.exception.CursoDAOException;
import br.com.franca.web.exception.CursoServiceException;

public class ContratoBusiness extends BusinessGeneric<Contrato, Long> {

	private ContratoDAO dao;

	public ContratoBusiness() {
		this.dao = new ContratoDAO();
	}

	public List<Contrato> findAll() throws CursoServiceException {
		try {
			return this.dao.findAll();
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}

	public Contrato find(Long id) throws CursoServiceException {
		// Contrato contrato;
		if (idIsNull(id)) {
			throw new CursoServiceException("ID não pode ser null.");
		}

		try {
			return this.dao.find(id);
			// contrato = this.dao.find(id);
			// return domainIsNull(contrato) ? null : contrato;
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}

	}

	public Contrato insert(Contrato contrato) throws CursoServiceException {

		if (domainIsNull(contrato)) {
			throw new CursoServiceException("Contrato não pode ser null.");
		}

		/*
		 * CondicaoDeContrato condicaoContrato =
		 * CondicaoDeContrato.getCondicaoContrato(contrato.getQtdParcelasCurso(),
		 * contrato.getQtdParcelasMaterial());
		 * 
		 * List<Parcela> listaDeParcelas = condicaoContrato.calculaParcelas(contrato);
		 * 
		 * contrato.setParcelas(listaDeParcelas);
		 */

		contrato.setParcelas(CondicaoDeContrato
				.getCondicaoContrato(contrato.getQtdParcelasCurso(), contrato.getQtdParcelasMaterial())
				.calculaParcelas(contrato));

		try {
			return this.dao.save(contrato);
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}

	public Contrato update(Contrato contrato) throws CursoServiceException {

		if (domainIsNull(contrato)) {
			throw new RuntimeException("Contrato não pode ser null.");
		}

		if (idIsNull(contrato.getId())) {
			throw new RuntimeException("ID não pode ser null.");
		}
		try {
			return contrato = this.dao.update(contrato);
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}

	public Contrato delete(Long id) throws CursoServiceException {
		/*
		 * if (idIsNull(id)) { throw new RuntimeException("ID não pode ser null."); }
		 * 
		 * Contrato contrato = this.dao.find(id);
		 */
		Contrato contrato = this.find(id);
		try {
			return domainIsNull(contrato) ? null : this.dao.delete(contrato);
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}

}
