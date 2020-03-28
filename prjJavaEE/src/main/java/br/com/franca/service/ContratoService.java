package br.com.franca.service;

import java.util.List;

import javax.inject.Inject;

import br.com.franca.dao.ContratoDAO;
import br.com.franca.dao.exceptions.CursoDAOException;
import br.com.franca.domain.CondicaoDeContrato;
import br.com.franca.domain.Contrato;
import br.com.franca.domain.Parcela;
import br.com.franca.service.exceptions.CursoServiceException;

public class ContratoService extends ServiceGeneric<Contrato> {

	@Inject
	private ContratoDAO dao;

	public ContratoService() {
		super(Contrato.class);
	}

	public List<Contrato> findAll() throws CursoServiceException {

		try {
			return dao.findAll();
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}

	public Contrato find(Long id) throws CursoServiceException {

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

	public Contrato save(Contrato contrato) throws CursoServiceException {

		List<Parcela> listaDeParcelas = this.simularContrato(contrato);

		try {
			return dao.save(contrato, listaDeParcelas);
		} catch (CursoDAOException e) {
			e.printStackTrace();
			throw new CursoServiceException(e);
		}
	}

	public Contrato update(Contrato contrato) throws CursoServiceException {

		if (domainIsNull(contrato)) {
			throw new CursoServiceException("Contrato não pode ser null.");
		}

		if (idIsNull(contrato.getId())) {
			throw new CursoServiceException("ID não pode ser null.");
		}

		try {
			return contrato = dao.update(contrato);
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}

	public void delete(Long id) throws CursoServiceException {

		try {

			Contrato contrato = find(id);

			if (domainIsNull(contrato)) {
				throw new CursoServiceException("Contrato não pode ser null");
			}

			dao.delete(contrato);

		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}

	public List<Parcela> simularContrato(Contrato contrato) throws CursoServiceException {

		if (domainIsNull(contrato))
			throw new CursoServiceException("Contrato não pode ser null");

		if (contrato.getDiaVencimento() == null)
			throw new CursoServiceException("Dia de vencimento é obrigatório");

		if (contrato.getFormaPagamento() == null)
			throw new CursoServiceException("Forma de pagamento é obrigatório");

		if (contrato.getQtdParcelasCurso() == null)
			throw new CursoServiceException("Parcelas de curso é obrigatório");

		if (contrato.getQtdParcelasMaterial() == null)
			throw new CursoServiceException("Parcelas de material é obrigatório");

		if (idIsNull(contrato.getAluno().getId()))
			throw new CursoServiceException("Aluno é obrigatório");

		if (idIsNull(contrato.getTurma().getId()))
			throw new CursoServiceException("Turma é obrigatório");

		return obterParcelas(contrato);

	}

	private List<Parcela> obterParcelas(Contrato contrato) {
		return CondicaoDeContrato.getCondicaoContrato(contrato.getQtdParcelasCurso(), contrato.getQtdParcelasMaterial())
				.calculaParcelas(contrato);
	}

}
