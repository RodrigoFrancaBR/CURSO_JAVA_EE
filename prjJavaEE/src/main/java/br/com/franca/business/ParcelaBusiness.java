package br.com.franca.business;

import java.util.List;

import javax.inject.Inject;

import br.com.franca.business.exceptions.CursoServiceException;
import br.com.franca.dao.exceptions.CursoDAOException;
import br.com.franca.dao.implement.DAOGeneric;
import br.com.franca.domain.Parcela;
import br.com.franca.domain.enun.SituacaoParcela;

public class ParcelaBusiness extends BusinessGeneric<Parcela, Long> {

	@Inject
	private DAOGeneric<Parcela> dao;

	public List<Parcela> findAll() throws CursoServiceException {
		try {
			return this.dao.findAll();
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}

	public Parcela find(Long id) throws CursoServiceException {
		if (idIsNull(id)) {
			throw new CursoServiceException("ID não pode ser null.");
		}

		try {
			return this.dao.find(id);
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
			throw new CursoServiceException("Parcela não pode ser null.");
		}

		if (idIsNull(parcela.getId())) {
			throw new CursoServiceException("ID não pode ser null.");
		}

		try {
			return parcela = this.dao.update(parcela);
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}

	public void delete(Long id) throws CursoServiceException {

		if (idIsNull(id)) {
			throw new RuntimeException("ID não pode ser null.");
		}

		Parcela parcela = this.find(id);

		if (domainIsNull(parcela)) {
			throw new CursoServiceException("Unidade não pode ser null.");
		}

		try {
			dao.delete(parcela);
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}
}
