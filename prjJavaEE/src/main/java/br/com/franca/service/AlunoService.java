package br.com.franca.service;

import java.util.List;

import javax.inject.Inject;

import br.com.franca.dao.AlunoDAO;
import br.com.franca.dao.DAOGeneric;
import br.com.franca.domain.Aluno;
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
			throw new CursoServiceException(Mensagem.getMessage("id_fornecido_null"));

		return dao.fimdById(id);
	}

	public Aluno save(Aluno aluno) throws CursoServiceException, CursoDAOException {

		if (aluno == null)
			throw new CursoServiceException(Mensagem.getMessage("entidade_fornecida_null"));

		if (nomeInvalido(aluno.getNome()))
			throw new CursoServiceException(Mensagem.getMessage("nome_fornecido_null"));
		
		String cpf = aluno.getCpf();
		Long parseLong = Long.parseLong(cpf);
		System.out.println(parseLong);
		
//		if (aluno.getCpf() == null || aluno.getCpf().trim().equals("") || aluno.getCpf().trim().length() != 11) {
//			throw new CursoServiceException(Mensagem.getMessage("cpf_invalido"));
//		}
		
//		if (aluno.getRg() != null && !aluno.getRg().trim().equals("") && aluno.getCpf().length() == 11) {
//			throw new CursoServiceException(Mensagem.getMessage("cpf_invalido"));
//		}
		// outras validações de negócio os campos not-null e unique

		aluno.setSituacao(SituacaoAluno.ATIVO);

		return dao.save(aluno);
	}

	public Aluno update(Aluno aluno) throws CursoServiceException, CursoDAOException {

		Aluno alunoEncontrado = null;

		if (aluno == null)
			throw new CursoServiceException(Mensagem.getMessage("entidade_fornecida_null"));

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

}
