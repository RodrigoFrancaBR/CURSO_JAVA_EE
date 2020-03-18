package br.com.franca.business;

import java.util.List;

import br.com.franca.business.exceptions.CursoServiceException;
import br.com.franca.dao.exceptions.CursoDAOException;
import br.com.franca.dao.implement.DAOGeneric;
import br.com.franca.domain.Parcela;
import br.com.franca.domain.enun.SituacaoParcela;

public class ParcelaBusiness extends BusinessGeneric<Parcela, Long> {

	private DAOGeneric<Parcela> dao;

	public List<Parcela> findAll() throws CursoServiceException {
		try {
			return this.dao.findAll();
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}

	public Parcela find(Long id) throws CursoServiceException {
		// Parcela parcela;
		if (idIsNull(id)) {
			throw new CursoServiceException("ID não pode ser null.");
		}

		try {
			return this.dao.find(id);
			// parcela = this.dao.find(id);
			// return domainIsNull(parcela) ? null : parcela;
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}

	}

	public Parcela insert(Parcela parcela) throws CursoServiceException {

		if (domainIsNull(parcela)) {
			throw new CursoServiceException("Parcela não pode ser null.");
		}

		parcela.setSituacao(SituacaoParcela.A_VENCER);

		try {
			return this.dao.save(parcela);
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}

	public Parcela update(Parcela parcela) throws CursoServiceException {

		if (domainIsNull(parcela)) {
			throw new RuntimeException("Parcela não pode ser null.");
		}

		if (idIsNull(parcela.getId())) {
			throw new RuntimeException("ID não pode ser null.");
		}
		try {
			return parcela = this.dao.update(parcela);
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}

	public Parcela delete(Long id) throws CursoServiceException {
		/*
		 * if (idIsNull(id)) { throw new RuntimeException("ID não pode ser null."); }
		 * 
		 * Parcela parcela = this.dao.find(id);
		 */
		Parcela parcela = this.find(id);
		try {
			return domainIsNull(parcela) ? null : this.dao.delete(parcela);
		} catch (CursoDAOException ex) {
			ex.printStackTrace();
			throw new CursoServiceException(ex);
		}
	}

	/*
	 * public List<Parcela> simularParcelas(Contrato contrato) throws
	 * CursoServiceException {
	 * 
	 * if (contrato.getDiaVencimento() == null) { throw new
	 * CursoServiceException("Dia de vencimento é obrigatório"); }
	 * 
	 * if (contrato.getAluno().getCpf() == null) { throw new
	 * CursoServiceException("CPF é obrigatório"); }
	 * 
	 * if (contrato.getTurma() == null) { throw new
	 * CursoServiceException("Turma é obrigatório"); }
	 * 
	 * if (contrato.getFormaPagamento() == null) { throw new
	 * CursoServiceException("Forma de pagamento é obrigatório"); }
	 * 
	 * if (contrato.getQtdParcelasCurso() == null) { throw new
	 * CursoServiceException("Parcelas de curso é obrigatório"); }
	 * 
	 * if (contrato.getQtdParcelasMaterial() == null) { throw new
	 * CursoServiceException("Parcelas de material é obrigatório"); }
	 * 
	 * CondicaoDeContrato condicaoContrato =
	 * CondicaoDeContrato.getCondicaoContrato(contrato.getQtdParcelasCurso(),
	 * contrato.getQtdParcelasMaterial()); return
	 * condicaoContrato.calculaParcelas(contrato);
	 * 
	 * }
	 */

	/*
	 * public List<Parcela> inserir(Contrato contrato) throws CursoServiceException
	 * { List<Parcela> listaDeParcelas = this.simularParcelas(contrato);
	 * List<Parcela> listaDeParcelasSalva = new ArrayList<>(); for (Parcela parcela
	 * : listaDeParcelas) { try { Parcela parcelaSalva = this.dao.save(parcela);
	 * listaDeParcelasSalva.add(parcelaSalva); } catch (CursoDAOException e) {
	 * e.printStackTrace(); throw new CursoServiceException(e); } } return
	 * listaDeParcelasSalva; }
	 */
}
