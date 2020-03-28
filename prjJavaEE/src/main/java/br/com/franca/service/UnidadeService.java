package br.com.franca.service;

import java.util.List;

import javax.inject.Inject;

import br.com.franca.dao.DAOGeneric;
import br.com.franca.dao.exceptions.CursoDAOException;
import br.com.franca.domain.Unidade;
import br.com.franca.domain.enun.Status;
import br.com.franca.service.exceptions.CursoServiceException;

public class UnidadeService extends ServiceGeneric<Unidade> {

	@Inject
	private DAOGeneric<Unidade> dao;

	public UnidadeService() {
		super(Unidade.class);
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
			return this.dao.insert(unidade);
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}

	public Unidade update(Unidade unidade) throws CursoServiceException {

		if (domainIsNull(unidade)) {
			throw new CursoServiceException("Unidade não pode ser null.");
		}

		if (idIsNull(unidade.getId())) {
			throw new CursoServiceException("ID não pode ser null.");
		}

		try {
			return unidade = this.dao.update(unidade);
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}

	public void delete(Long id) throws CursoServiceException {

		try {

			Unidade unidade = find(id);

			if (domainIsNull(unidade)) {
				throw new CursoServiceException("Unidade não pode ser null");
			}

			dao.delete(unidade);

		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}
}
