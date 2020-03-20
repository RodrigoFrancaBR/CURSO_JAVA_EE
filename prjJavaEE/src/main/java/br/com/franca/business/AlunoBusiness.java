package br.com.franca.business;

import java.util.List;

import javax.inject.Inject;

import br.com.franca.business.exceptions.CursoServiceException;
import br.com.franca.dao.exceptions.CursoDAOException;
import br.com.franca.dao.implement.DAOGeneric;
import br.com.franca.domain.Aluno;
import br.com.franca.domain.enun.SituacaoAluno;

public class AlunoBusiness extends BusinessGeneric<Aluno, Long> {

	@Inject
	private DAOGeneric<Aluno> dao;

	public List<Aluno> findAll() throws CursoServiceException {
		try {
			return this.dao.findAll();
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}

	public Aluno find(Long id) throws CursoServiceException {

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

	public Aluno insert(Aluno aluno) throws CursoServiceException {

		if (domainIsNull(aluno)) {
			throw new CursoServiceException("Aluno não pode ser null.");
		}

		if (nomeIsInvalid(aluno.getNome())) {
			throw new CursoServiceException("Nome não pode ser null.");
		}

		aluno.setSituacao(SituacaoAluno.INATIVO);

		try {
			return this.dao.save(aluno);
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}

	public Aluno update(Aluno aluno) throws CursoServiceException {

		if (domainIsNull(aluno)) {
			throw new CursoServiceException("Aluno não pode ser null.");
		}

		if (idIsNull(aluno.getId())) {
			throw new CursoServiceException("ID não pode ser null.");
		}
		try {
			return aluno = this.dao.update(aluno);
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}

	public void delete(Long id) throws CursoServiceException {

		if (idIsNull(id)) {
			throw new CursoServiceException("ID não pode ser null.");
		}

		Aluno aluno = this.find(id);

		if (domainIsNull(aluno)) {
			throw new CursoServiceException("Aluno não pode ser null.");
		}

		try {
			this.dao.delete(aluno);
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}

}
