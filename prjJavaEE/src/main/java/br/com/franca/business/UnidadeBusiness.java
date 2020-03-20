package br.com.franca.business;

import java.util.List;

import javax.inject.Inject;

import br.com.franca.business.exceptions.CursoServiceException;
import br.com.franca.dao.exceptions.CursoDAOException;
import br.com.franca.dao.implement.DAOGeneric;
import br.com.franca.domain.Unidade;
import br.com.franca.domain.enun.Status;

public class UnidadeBusiness extends BusinessGeneric<Unidade, Long> {

	private TurmaBusiness turmaBusiness = new TurmaBusiness();

	private DAOGeneric<Unidade> dao;

	@Inject
	public UnidadeBusiness(DAOGeneric<Unidade> dao) {
		this.dao = dao;
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
			return this.dao.save(unidade);
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

	@Inject
	public void delete(Long id) throws CursoServiceException {

		if (idIsNull(id)) {
			throw new CursoServiceException("ID não pode ser null.");
		}

		Unidade unidade = this.find(id);

		if (domainIsNull(unidade)) {
			throw new CursoServiceException("Unidade não pode ser null.");
		}

		turmaBusiness.findAllTurmasByUnidadeId(unidade.getId());

		try {
			dao.delete(unidade);
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}
}
