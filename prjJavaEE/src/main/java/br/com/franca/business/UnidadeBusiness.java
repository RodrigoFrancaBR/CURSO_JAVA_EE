package br.com.franca.business;

import java.util.List;

import br.com.franca.dao.implement.UnidadeDAO;
import br.com.franca.domain.Unidade;
import br.com.franca.domain.enun.Status;
import br.com.franca.web.exception.CursoDAOException;
import br.com.franca.web.exception.CursoServiceException;

public class UnidadeBusiness extends BusinessGeneric<Unidade, Long> {

	private UnidadeDAO dao;

	public UnidadeBusiness() {
		this.dao = new UnidadeDAO();
	}

	public List<Unidade> findAll() throws CursoServiceException {
		try {
			return this.dao.findAll();
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}

	public Unidade find(Long id) throws CursoServiceException {
		// Unidade unidade;
		if (idIsNull(id)) {
			throw new CursoServiceException("ID não pode ser null.");
		}

		try {
			return this.dao.find(id);
			// unidade = this.dao.find(id);
			// return domainIsNull(unidade) ? null : unidade;
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}

	}

	public Unidade insert(Unidade unidade) throws CursoServiceException {

		if (domainIsNull(unidade)) {
			throw new CursoServiceException("Unidade não pode ser null.");
		}

		if (nomeIsInvalid(unidade.getNome())) {
			throw new CursoServiceException("Nome não pode ser null.");
		}

		if (enderecoIsInvalid(unidade.getEndereco())) {
			throw new CursoServiceException("Endereço não pode ser null.");
		}

		unidade.setStatus(Status.ATIVA);

		try {
			return this.dao.save(unidade);
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}

	public Unidade update(Unidade unidade) throws CursoServiceException {

		if (domainIsNull(unidade)) {
			throw new RuntimeException("Unidade não pode ser null.");
		}

		if (idIsNull(unidade.getId())) {
			throw new RuntimeException("ID não pode ser null.");
		}
		try {
			return unidade = this.dao.update(unidade);
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}

	public Unidade delete(Long id) throws CursoServiceException {
		/*
		 * if (idIsNull(id)) { throw new RuntimeException("ID não pode ser null."); }
		 * 
		 * Unidade unidade = this.dao.find(id);
		 */
		Unidade unidade = this.find(id);
		try {
			return domainIsNull(unidade) ? null : this.dao.delete(unidade);
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}
}
