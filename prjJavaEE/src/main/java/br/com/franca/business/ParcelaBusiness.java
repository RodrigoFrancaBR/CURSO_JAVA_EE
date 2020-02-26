package br.com.franca.business;

import java.util.List;

import br.com.franca.dao.implement.ParcelaDAO;
import br.com.franca.domain.Parcela;
import br.com.franca.domain.enun.SituacaoParcela;
import br.com.franca.web.exception.CursoDAOException;
import br.com.franca.web.exception.CursoServiceException;

public class ParcelaBusiness extends BusinessGeneric<Parcela, Long> {

	private ParcelaDAO dao;

	public ParcelaBusiness() {
		this.dao = new ParcelaDAO();
	}

	public List<Parcela> findAll() throws CursoServiceException {
		try {
			return this.dao.findAll();
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}

	public Parcela find(Long id) throws CursoServiceException {
		// Parcela parcela;
		if (idIsNull(id)) {
			throw new CursoServiceException("ID não pode ser null.");
		}

		try {
			return this.dao.find(id);
			// parcela = this.dao.find(id);
			// return domainIsNull(parcela) ? null : parcela;
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}

	}

	public Parcela insert(Parcela parcela) throws CursoServiceException {

		if (domainIsNull(parcela)) {
			throw new CursoServiceException("Parcela não pode ser null.");
		}

		parcela.setSituacao(SituacaoParcela.A_VENCER);

		try {
			return this.dao.save(parcela);
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}

	public Parcela update(Parcela parcela) throws CursoServiceException {

		if (domainIsNull(parcela)) {
			throw new RuntimeException("Parcela não pode ser null.");
		}

		if (idIsNull(parcela.getId())) {
			throw new RuntimeException("ID não pode ser null.");
		}
		try {
			return parcela = this.dao.update(parcela);
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}

	public Parcela delete(Long id) throws CursoServiceException {
		/*
		 * if (idIsNull(id)) { throw new RuntimeException("ID não pode ser null."); }
		 * 
		 * Parcela parcela = this.dao.find(id);
		 */
		Parcela parcela = this.find(id);
		try {
			return domainIsNull(parcela) ? null : this.dao.delete(parcela);
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}
}
