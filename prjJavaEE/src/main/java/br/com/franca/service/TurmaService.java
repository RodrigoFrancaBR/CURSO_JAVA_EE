package br.com.franca.service;

import java.util.List;

import javax.inject.Inject;

import br.com.franca.dao.TurmaDAO;
import br.com.franca.domain.Turma;
import br.com.franca.domain.enun.Status;
import br.com.franca.exceptions.CursoDAOException;
import br.com.franca.exceptions.CursoServiceException;
import br.com.franca.msg.Mensagem;

public class TurmaService extends CommonServiceValidations {

	@Inject
	private TurmaDAO dao;

	public List<Turma> findAll() throws CursoDAOException {
		return this.dao.findAll();
	}

	public Turma findById(Long id) throws CursoServiceException, CursoDAOException {

		if (id == null)
			throw new CursoServiceException(Mensagem.getMessage("id_fornecido_null"));

		return dao.fimdById(id);
	}

	public Turma save(Turma turma) throws CursoServiceException, CursoDAOException {

		if (turma == null)
			throw new CursoServiceException(Mensagem.getMessage("entidade_fornecida_null"));

		if (nomeIsInvalid(turma.getNome()))
			throw new CursoServiceException(Mensagem.getMessage("nome_fornecido_null"));

		// aplicar regras de unique

		turma.setStatus(Status.ATIVA);

		return this.dao.save(turma);
	}

	public Turma update(Turma turma) throws CursoServiceException, CursoDAOException {

		Turma turmaEncontrada = null;

		if (turma == null)
			throw new CursoServiceException(Mensagem.getMessage("entidade_fornecida_null"));

		turmaEncontrada = findById(turma.getId());

		if (turmaEncontrada == null)
			throw new CursoServiceException(Mensagem.getMessage("entidade_nao_encontrada"));

		return turma = this.dao.update(turma);
	}

	public void delete(Long id) throws CursoServiceException, CursoDAOException {

		Turma turmaEncontrada = null;

		turmaEncontrada = findById(id);

		if (turmaEncontrada == null)
			throw new CursoServiceException(Mensagem.getMessage("entidade_nao_encontrada"));

		turmaEncontrada.setStatus(Status.DESATIVADA);
		
		dao.delete(turmaEncontrada);		
	}
}
