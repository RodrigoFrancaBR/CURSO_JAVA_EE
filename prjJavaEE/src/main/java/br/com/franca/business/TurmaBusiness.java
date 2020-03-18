package br.com.franca.business;

import java.util.List;

import br.com.franca.business.exceptions.CursoServiceException;
import br.com.franca.dao.exceptions.CursoDAOException;
import br.com.franca.dao.implement.DAOGeneric;
import br.com.franca.dao.implement.TurmaDAO;
import br.com.franca.domain.Turma;
import br.com.franca.domain.enun.Status;

public class TurmaBusiness extends BusinessGeneric<Turma, Long> {

	private DAOGeneric<Turma> dao= new TurmaDAO();

	public List<Turma> findAll() throws CursoServiceException {
		try {
			return this.dao.findAll();
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}

	public Turma find(Long id) throws CursoServiceException {
		// Turma turma;
		if (idIsNull(id)) {
			throw new CursoServiceException("ID não pode ser null.");
		}

		try {
			return this.dao.find(id);
			// turma = this.dao.find(id);
			// return domainIsNull(turma) ? null : turma;
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}

	}

	public Turma insert(Turma turma) throws CursoServiceException {

		if (domainIsNull(turma)) {
			throw new CursoServiceException("Turma não pode ser null.");
		}

		if (nomeIsInvalid(turma.getNome())) {
			throw new CursoServiceException("Nome não pode ser null.");
		}

		turma.setStatus(Status.ATIVA);

		try {
			return this.dao.save(turma);
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}

	public Turma update(Turma turma) throws CursoServiceException {

		if (domainIsNull(turma)) {
			throw new RuntimeException("Turma não pode ser null.");
		}

		if (idIsNull(turma.getId())) {
			throw new RuntimeException("ID não pode ser null.");
		}
		try {
			return turma = this.dao.update(turma);
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}

	public Turma delete(Long id) throws CursoServiceException {
		/*
		 * if (idIsNull(id)) { throw new RuntimeException("ID não pode ser null."); }
		 * 
		 * Turma turma = this.dao.find(id);
		 */
		Turma turma = this.find(id);
		try {
			return domainIsNull(turma) ? null : this.dao.delete(turma);
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}

}
