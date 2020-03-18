package br.com.franca.business;

import java.util.List;

import br.com.franca.business.exceptions.CursoServiceException;
import br.com.franca.dao.exceptions.CursoDAOException;
import br.com.franca.dao.implement.ContratoDAO;
import br.com.franca.dao.interfaces.ContratoDAOI;
import br.com.franca.domain.CondicaoDeContrato;
import br.com.franca.domain.Contrato;
import br.com.franca.domain.Parcela;

public class ContratoBusiness extends BusinessGeneric<Contrato, Long> {

	private ContratoDAOI dao = new ContratoDAO();

	public List<Contrato> findAll() throws CursoServiceException {
		try {
			return this.dao.findAll();
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
			return this.dao.find(id);
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}

	}

	public Contrato save(Contrato contrato) throws CursoServiceException {

		List<Parcela> listaDeParcelas = this.simularContrato(contrato);

		try {
			return this.dao.save(contrato, listaDeParcelas);
		} catch (CursoDAOException e) {
			e.printStackTrace();
			throw new CursoServiceException(e);
		}
	}

	public Contrato update(Contrato contrato) throws CursoServiceException {

		if (domainIsNull(contrato)) {
			throw new RuntimeException("Contrato não pode ser null.");
		}

		if (idIsNull(contrato.getId())) {
			throw new RuntimeException("ID não pode ser null.");
		}
		try {
			return contrato = this.dao.update(contrato);
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}

	public Contrato delete(Long id) throws CursoServiceException {
		Contrato contrato = this.find(id);
		try {
			return domainIsNull(contrato) ? null : this.dao.delete(contrato);
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}

	public List<Parcela> simularContrato(Contrato contrato) throws CursoServiceException {
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

		return this.obterParcelas(contrato);

	}

	private List<Parcela> obterParcelas(Contrato contrato) {
		return CondicaoDeContrato.getCondicaoContrato(contrato.getQtdParcelasCurso(), contrato.getQtdParcelasMaterial())
				.calculaParcelas(contrato);
	}

}
