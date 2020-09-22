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

		Unidade unidade = dao.fimdById(id);

		if (unidade == null)
			throw new CursoServiceException(Mensagem.getMessage("entidade_nao_encontrada"));

		return unidade;

	}

	public Unidade save(Unidade unidade) throws CursoServiceException, CursoDAOException {

		if (unidade == null)
			throw new CursoServiceException(Mensagem.getMessage("entidade_null"));

		if (nomeInvalido(unidade.getNome()))
			throw new CursoServiceException(Mensagem.getMessage("nome_invalido"));

		if (enderecoInvalido(unidade.getEndereco()))
			throw new CursoServiceException(Mensagem.getMessage("endereco_invalido"));

		// aplicar regras de unique

		unidade.setStatus(Status.ATIVA);

		return this.dao.save(unidade);
	}

	public Unidade update(Long id, Unidade unidade) throws CursoServiceException, CursoDAOException {

		Unidade unidadeEncontrada = null;

		if (unidade == null)
			throw new CursoServiceException(Mensagem.getMessage("entidade_null"));

		unidadeEncontrada = findById(id);

		if (unidadeEncontrada == null)
			throw new CursoServiceException(Mensagem.getMessage("entidade_nao_encontrada"));

		if (nomeInvalido(unidade.getNome()))
			throw new CursoServiceException(Mensagem.getMessage("nome_invalido"));

		if (enderecoInvalido(unidade.getEndereco()))
			throw new CursoServiceException(Mensagem.getMessage("endereco_invalido"));

		if (statusInvalido(unidade.getStatus()))
			throw new CursoServiceException(Mensagem.getMessage("status_invalido"));

		configurarObjetoAntesDeAtualizar(unidade, unidadeEncontrada);

		return this.dao.update(unidadeEncontrada);
	}

	public void delete(Long id) throws CursoServiceException, CursoDAOException {

		Unidade unidadeEncontrada = null;

		unidadeEncontrada = findById(id);

		if (unidadeEncontrada == null)
			throw new CursoServiceException(Mensagem.getMessage("entidade_nao_encontrada"));

		unidadeEncontrada.setStatus(Status.DESATIVADA);

		dao.delete(unidadeEncontrada);
	}

	private void configurarObjetoAntesDeAtualizar(Unidade unidade, Unidade unidadeEncontrada) {
		unidadeEncontrada.setNome(unidade.getNome());
		unidadeEncontrada.setEndereco(unidade.getEndereco());
		unidadeEncontrada.setStatus(unidade.getStatus());
	}
}
