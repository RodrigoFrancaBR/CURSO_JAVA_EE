package br.com.franca.service;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import br.com.franca.dao.ContratoDAO;
import br.com.franca.domain.Aluno;
import br.com.franca.domain.CondicaoDeContrato;
import br.com.franca.domain.Contrato;
import br.com.franca.domain.Parcela;
import br.com.franca.domain.Turma;
import br.com.franca.domain.enun.SituacaoMatricula;
import br.com.franca.exceptions.CursoDAOException;
import br.com.franca.exceptions.CursoServiceException;
import br.com.franca.msg.Mensagem;

public class ContratoService extends CommonServiceValidations {

	@Inject
	private ContratoDAO dao;
	// private ContratoRepository dao;
	// private DAOGeneric<Contrato> dao;

	@Inject
	private AlunoService alunoService;

	@Inject
	TurmaService turmaService;

	public List<Contrato> findAll() throws CursoDAOException {
		return dao.findAll();
	}

	public Contrato findById(Long id) throws CursoServiceException, CursoDAOException {

		if (id == null)
			throw new CursoServiceException(Mensagem.getMessage("id_fornecido_null"));

		return dao.fimdById(id);
	}

	public Contrato save(Contrato contrato) throws CursoServiceException, CursoDAOException {

		Aluno aluno = alunoService.findById(contrato.getAluno().getId());
		Turma turma = turmaService.findById(contrato.getTurma().getId());

		if (aluno == null)
			throw new CursoServiceException(Mensagem.getMessage("entidade_nao_encontrada"));

		if (turma == null)
			throw new CursoServiceException(Mensagem.getMessage("entidade_nao_encontrada"));

		contrato.setAluno(aluno);
		contrato.setTurma(turma);
		contrato.setMatricula(obterMatricula(contrato));

		List<Parcela> listaDeParcelas = this.simularContrato(contrato);

		return dao.save(contrato, listaDeParcelas);
	}

	private String obterMatricula(Contrato contrato) {
		int anoAtual = contrato.getDataMatricula().get(Calendar.YEAR);
		String cpfInicio = contrato.getAluno().getCpf().substring(0, 3);
		String turmaNome = contrato.getTurma().getNome();
		String matricula = anoAtual + cpfInicio + turmaNome;
		return matricula;
	}

	public void delete(Long id) throws CursoServiceException, CursoDAOException {

		Contrato contratoEncontrado = null;

		contratoEncontrado = findById(id);

		if (contratoEncontrado == null)
			throw new CursoServiceException(Mensagem.getMessage("entidade_nao_encontrada"));

		contratoEncontrado.setSituacaoMatricula(SituacaoMatricula.CANCELADA);

		dao.delete(contratoEncontrado);
	}

	public List<Parcela> simularContrato(Contrato contrato) throws CursoServiceException {

		if (contrato == null)
			throw new CursoServiceException(Mensagem.getMessage("entidade_fornecida_null"));

		if (contrato.getDiaVencimento() == null)
			throw new CursoServiceException(Mensagem.getMessage("dia_vencimento_null"));

		if (contrato.getFormaPagamento() == null)
			throw new CursoServiceException(Mensagem.getMessage("forma_pagamento_null"));

		if (contrato.getQtdParcelasCurso() == null)
			throw new CursoServiceException(Mensagem.getMessage("qtd_parc_curso_null"));

		if (contrato.getQtdParcelasMaterial() == null)
			throw new CursoServiceException(Mensagem.getMessage("qtd_parc_material_null"));

		return obterParcelas(contrato);

	}

	private List<Parcela> obterParcelas(Contrato contrato) {
		return CondicaoDeContrato.getCondicaoContrato(contrato.getQtdParcelasCurso(), contrato.getQtdParcelasMaterial())
				.calculaParcelas(contrato);
	}

}
