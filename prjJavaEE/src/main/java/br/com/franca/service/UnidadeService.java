package br.com.franca.service;

import java.util.List;

import javax.inject.Inject;

import br.com.franca.dao.DAOGeneric;
import br.com.franca.domain.Unidade;
import br.com.franca.domain.enun.Status;
import br.com.franca.exceptions.CursoDAOException;
import br.com.franca.exceptions.CursoServiceException;
import br.com.franca.msg.Mensagem;

public class UnidadeService extends CommonServiceValidations {

	@Inject
	private DAOGeneric<Unidade> dao;

	public List<Unidade> findAll() throws CursoDAOException {
		return dao.findAll();
	}

	public Unidade findById(Long id) throws CursoServiceException, CursoDAOException {

		if (id == null)
			throw new CursoServiceException(Mensagem.getMessage("id_fornecido_null"));

		return dao.fimdById(id);
	}

	public Unidade save(Unidade unidade) throws CursoServiceException, CursoDAOException {

		if (unidade == null)
			throw new CursoServiceException(Mensagem.getMessage("entidade_fornecida_null"));

		if (nomeIsInvalid(unidade.getNome()))
			throw new CursoServiceException(Mensagem.getMessage("nome_fornecido_null"));

		if (enderecoIsInvalid(unidade.getEndereco()))
			throw new CursoServiceException(Mensagem.getMessage("endereco_fornecido_null"));

		// aplicar regras de unique

		unidade.setStatus(Status.ATIVA);

		return this.dao.save(unidade);
	}

	public Unidade update(Unidade unidade) throws CursoServiceException, CursoDAOException {

		Unidade unidadeEncontrada = null;

		if (unidade == null)
			throw new CursoServiceException(Mensagem.getMessage("entidade_fornecida_null"));

		unidadeEncontrada = findById(unidade.getId());

		if (unidadeEncontrada == null)
			throw new CursoServiceException(Mensagem.getMessage("entidade_nao_encontrada"));

		if (nomeIsInvalid(unidade.getNome()))
			throw new CursoServiceException(Mensagem.getMessage("nome_fornecido_null"));

		if (enderecoIsInvalid(unidade.getEndereco()))
			throw new CursoServiceException(Mensagem.getMessage("endereco_fornecido_null"));

		if (statusInvalido(unidade.getStatus()))
			throw new CursoServiceException(Mensagem.getMessage("status_fornecido_null"));

		return this.dao.update(unidade);
	}

	public void delete(Long id) throws CursoServiceException, CursoDAOException {

		Unidade unidadeEncontrada = null;

		unidadeEncontrada = findById(id);

		if (unidadeEncontrada == null)
			throw new CursoServiceException(Mensagem.getMessage("entidade_nao_encontrada"));

		unidadeEncontrada.setStatus(Status.DESATIVADA);

		dao.delete(unidadeEncontrada);
	}
}
