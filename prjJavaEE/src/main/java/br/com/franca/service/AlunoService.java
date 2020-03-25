package br.com.franca.service;

import java.util.List;

import javax.inject.Inject;

import br.com.franca.business.exceptions.CursoServiceException;
import br.com.franca.dao.AlunoDAO;
import br.com.franca.dao.DAOGeneric;
import br.com.franca.dao.exceptions.CursoDAOException;
import br.com.franca.domain.Aluno;
import br.com.franca.domain.enun.SituacaoAluno;

public class AlunoService extends ServiceGeneric<Aluno> {

	@Inject
	private DAOGeneric<Aluno> dao = new AlunoDAO();

	public AlunoService() {
		super(Aluno.class);
	}

	public List<Aluno> findAll() throws CursoServiceException {
		try {
			return dao.findAll();
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
			return dao.find(id);
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
			return dao.insert(aluno);
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
			return aluno = dao.update(aluno);
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}

	public void delete(Long id) throws CursoServiceException {

		try {

			Aluno aluno = find(id);

			if (domainIsNull(aluno)) {
				throw new CursoServiceException("Aluno não pode ser null");
			}

			dao.delete(aluno);

		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}

}
