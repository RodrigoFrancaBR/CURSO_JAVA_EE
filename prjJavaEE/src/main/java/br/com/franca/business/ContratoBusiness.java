package br.com.franca.business;

import java.util.ArrayList;
import java.util.List;

import br.com.franca.dao.implement.ContratoDAO;
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

		contrato.setParcelas(this.getCondicaoDoContrato(contrato));

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

	public List<Parcela> getCondicaoDoContrato(Contrato contrato) {
		// List<Parcela> listaDeParcelas = new ArrayList<Parcela>();
		if (contrato.getQtdParcelasCurso() == 1 && contrato.getQtdParcelasMaterial() == 1) {
			return new CursoMaterialAvista(contrato).getListaDeParcelas();

			/*
			 * } else if (qtdParcelasCurso == 1 && qtdParcelasMaterial >= 2) {
			 * this.condicaoDoContrato = new CursoAvistaMaterialParcelado();
			 * this.condicaoContratoEnum = CondicaoContrato.CURSO_AVISTA_MATERIAL_PARCELADO;
			 * } else { if (qtdParcelasCurso >= 2 && qtdParcelasMaterial == 1) {
			 * this.condicaoDoContrato = new CursoParceladoMaterialAvista();
			 * this.condicaoContratoEnum = CondicaoContrato.CURSO_PARCELADO_MATERIAL_AVISTA;
			 * } else { if (qtdParcelasCurso >= 2 && qtdParcelasMaterial >= 2) {
			 * this.condicaoContratoEnum = CondicaoContrato.CURSO_MATERIAL_PARCELADO;
			 * this.condicaoDoContrato = new CursoMaterialParcelado(); } }
			 */

		}
		return new ArrayList<Parcela>();
	}
}
