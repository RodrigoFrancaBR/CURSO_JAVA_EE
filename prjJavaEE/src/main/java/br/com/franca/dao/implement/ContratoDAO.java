package br.com.franca.dao.implement;

import java.util.List;

import br.com.franca.dao.interfaces.ContratoDAOI;
import br.com.franca.domain.Contrato;
import br.com.franca.domain.Parcela;
import br.com.franca.web.exception.CursoDAOException;

public class ContratoDAO extends DAOGeneric<Contrato, Long> implements ContratoDAOI {

	public ContratoDAO() {
		super();
		this.dominio = Contrato.class;
	}

	public Contrato save(Contrato contrato, List<Parcela> listaDeParcelas) throws CursoDAOException {
		try {
			em.getTransaction().begin();
			em.persist(contrato);
			// parcelas.forEach(p -> em.persist(p));
			// parcelas.forEach(EntityManager::persist); errado
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
