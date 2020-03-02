package br.com.franca.business;

import java.util.List;

import br.com.franca.dao.implement.ContratoDAO;
import br.com.franca.domain.CondicaoDeContrato;
import br.com.franca.domain.Contrato;
import br.com.franca.domain.Parcela;
import br.com.franca.web.exception.CursoDAOException;
import br.com.franca.web.exception.CursoServiceException;

public class ContratoBusiness extends BusinessGeneric<Contrato, Long> {

	private ContratoDAO contratoDAO;

	public ContratoBusiness() {
		this.contratoDAO = new ContratoDAO();
	}

	public List<Contrato> findAll() throws CursoServiceException {
		try {
			return this.contratoDAO.findAll();
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}

	public Contrato find(Long id) throws CursoServiceException {
		// Contrato contrato;
		if (idIsNull(id)) {
			throw new CursoServiceException("ID não pode ser null.");
		}

		try {
			return this.contratoDAO.find(id);
			// contrato = this.dao.find(id);
			// return domainIsNull(contrato) ? null : contrato;
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}

	}

	public Contrato save(Contrato contrato) throws CursoServiceException {

		/*
		 * if (domainIsNull(contrato)) { throw new
		 * CursoServiceException("Contrato não pode ser null."); }
		 */

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

		List<Parcela> listaDeParcelas = this.obterParcelas(contrato);

		boolean todosCombinam = listaDeParcelas.parallelStream()
				.allMatch(p -> p.getContrato().getId().equals(contrato.getId()));

		if (!todosCombinam) {
			throw new CursoServiceException("Todas as parcelas devem pertencer ao mesmo contrato");
		}

		try {
			return this.contratoDAO.save(contrato, listaDeParcelas);
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
			return contrato = this.contratoDAO.update(contrato);
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}

	public Contrato delete(Long id) throws CursoServiceException {
		/*
		 * if (idIsNull(id)) { throw new RuntimeException("ID não pode ser null."); }
		 * 
		 * Contrato contrato = this.dao.find(id);
		 */
		Contrato contrato = this.find(id);
		try {
			return domainIsNull(contrato) ? null : this.contratoDAO.delete(contrato);
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}

	private List<Parcela> obterParcelas(Contrato contrato) {
		return CondicaoDeContrato.getCondicaoContrato(contrato.getQtdParcelasCurso(), contrato.getQtdParcelasMaterial())
				.calculaParcelas(contrato);
	}

}
