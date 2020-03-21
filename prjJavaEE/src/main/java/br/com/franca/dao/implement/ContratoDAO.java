package br.com.franca.dao.implement;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.franca.dao.exceptions.CursoDAOException;
import br.com.franca.dao.interfaces.ContratoDAOI;
import br.com.franca.domain.Contrato;
import br.com.franca.domain.Parcela;

// public class ContratoDAO extends DAOGeneric<Contrato> implements ContratoDAOI {
public class ContratoDAO extends DAOGeneric<Contrato> implements ContratoDAOI {
	private static EntityManager em;

	public ContratoDAO() {
		super(Contrato.class, em);
	}

	public Contrato save(Contrato contrato, List<Parcela> listaDeParcelas) throws CursoDAOException {
		try {
			em.getTransaction().begin();
			em.persist(contrato);
			// parcelas.forEach(p -> em.persist(p)); //
			// parcelas.forEach(EntityManager::persist); errado
			listaDeParcelas.forEach(p -> p.setContrato(contrato));
			listaDeParcelas.forEach(em::persist);
			em.getTransaction().commit();
			return contrato;
		} catch (Exception ex) {
			roolback();
			ex.getStackTrace();
			throw new CursoDAOException(ex);
		}
	}

}
