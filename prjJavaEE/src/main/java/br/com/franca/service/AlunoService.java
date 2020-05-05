package br.com.franca.service;

import java.util.List;

import javax.inject.Inject;

import br.com.franca.dao.AlunoDAO;
import br.com.franca.dao.DAOGeneric;
import br.com.franca.domain.Aluno;
import br.com.franca.domain.enun.Sexo;
import br.com.franca.domain.enun.SituacaoAluno;
import br.com.franca.exceptions.CursoDAOException;
import br.com.franca.exceptions.CursoServiceException;
import br.com.franca.msg.Mensagem;

public class AlunoService extends CommonServiceValidations {

	@Inject
	private DAOGeneric<Aluno> dao = new AlunoDAO();

	public List<Aluno> findAll() throws CursoDAOException {
		return dao.findAll();
	}

	public Aluno findById(Long id) throws CursoServiceException, CursoDAOException {

		if (id == null)
			throw new CursoServiceException(Mensagem.getMessage("id_informado_null"));

		return dao.fimdById(id);
	}

	public Aluno save(Aluno aluno) throws CursoServiceException, CursoDAOException {

		if (aluno == null)
			throw new CursoServiceException(Mensagem.getMessage("entidade_null"));

		if (nomeInvalido(aluno.getNome()))
			throw new CursoServiceException(Mensagem.getMessage("nome_invalido"));

		if (documentoInvalido(aluno.getCpf(), 11))
			throw new CursoServiceException(Mensagem.getMessage("cpf_invalido"));

		if (documentoInvalido(aluno.getRg(), 9))
			throw new CursoServiceException(Mensagem.getMessage("rg_invalido"));

		if (aluno.getSexo() == null || aluno.getSexo().equals(Sexo.INVALIDO))
			throw new CursoServiceException(Mensagem.getMessage("sexo_invalido"));

		if (telefoneInvalido(aluno.getResidencial(), 10))
			throw new CursoServiceException(Mensagem.getMessage("residencial_invalido"));

		if (telefoneInvalido(aluno.getCelular(), 11))
			throw new CursoServiceException(Mensagem.getMessage("celular_invalido"));

		if (enderecoInvalido(aluno.getEndereco()))
			throw new CursoServiceException(Mensagem.getMessage("endereco_invalido"));

		if (aluno.getBairro() == null || aluno.getBairro().trim().equals(""))
			throw new CursoServiceException(Mensagem.getMessage("bairro_invalido"));

		// outras validações de negócio os campos not-null e unique

		aluno.setSituacao(SituacaoAluno.ATIVO);

		return dao.save(aluno);
	}

	public Aluno update(Aluno aluno) throws CursoServiceException, CursoDAOException {

		Aluno alunoEncontrado = null;

		if (aluno == null)
			throw new CursoServiceException(Mensagem.getMessage("entidade_informado_null"));

		alunoEncontrado = findById(aluno.getId());

		if (alunoEncontrado == null)
			throw new CursoServiceException(Mensagem.getMessage("entidade_nao_encontrada"));

		return dao.update(aluno);
	}

	public void delete(Long id) throws CursoServiceException, CursoDAOException {

		Aluno alunoEncontrado = null;

		alunoEncontrado = findById(id);

		if (alunoEncontrado == null)
			throw new CursoServiceException(Mensagem.getMessage("entidade_nao_encontrada"));

		alunoEncontrado.setSituacao(SituacaoAluno.INATIVO);

		dao.delete(alunoEncontrado);
	}

	public boolean telefoneInvalido(String telefone, int qtdDigitos) {
		if (telefone != null && telefone.trim().length() == qtdDigitos) {
			try {
				Long parseLong = Long.parseLong(telefone);
				return false;
			} catch (NumberFormatException ex) {
				return true;
			}
		}
		return true;
	}

	public boolean documentoInvalido(String documento, int qtdDigitos) {
		if (documento != null && documento.trim().length() == qtdDigitos) {
			try {
				Long parseLong = Long.parseLong(documento);
				return false;
			} catch (NumberFormatException ex) {
				// ex.getStackTrace();
				return true;
			}
		}
		return true;
	}
}
