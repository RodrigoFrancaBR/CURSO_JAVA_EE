package br.com.franca.dao.implement;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.franca.dao.interfaces.CRUDI;
import br.com.franca.util.EntityManagerUtil;
import br.com.franca.web.exception.CursoDAOException;

public class DAOGeneric<Dominio, Id> implements CRUDI<Dominio, Id> {

	public EntityManager em;
	public Class<Dominio> dominio;
	public String mensagem = "";

	public DAOGeneric() {
		this.em = EntityManagerUtil.getEntityManager();
	}

	public void roolback() {
		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
		}
		em.getTransaction().rollback();
	}
	
	@Override
	public Dominio find(Id id) throws CursoDAOException {
		try {
			return em.find(dominio, id);
		} catch (Exception e) {
			throw new CursoDAOException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dominio> findAll() throws CursoDAOException {
		/*
		 * String jpql = "select u from " + dominio.getSimpleName() + " u"; Query query
		 * = em.createQuery(jpql); List<Dominio> resultList = query.getResultList();
		 * return resultList;
		 */
		// return em.createQuery("from " + dominio.getSimpleName()).getResultList();
		try {
			return em.createQuery("select u from " + dominio.getSimpleName() + " u").getResultList();
		} catch (Exception e) {
			throw new CursoDAOException(e);
		}
	}

	@Override
	public Dominio save(Dominio dominio) throws CursoDAOException {
		try {
			em.getTransaction().begin();
			em.persist(dominio);
			em.getTransaction().commit();
		} catch (Exception ex) {
			roolback();
			ex.getStackTrace();
			throw new CursoDAOException(ex);
		}
		return dominio;
	}

	@Override
	public Dominio update(Dominio dominio) throws CursoDAOException {
		try {
			em.getTransaction().begin();
			em.merge(dominio);
			em.getTransaction().commit();
		} catch (Exception ex) {
			roolback();
			ex.getStackTrace();
			throw new CursoDAOException(ex);
		}
		return dominio;
	}

	@Override
	public Dominio delete(Dominio dominio) throws CursoDAOException {
		try {
			em.getTransaction().begin();
			em.remove(dominio);
			em.getTransaction().commit();
		} catch (Exception ex) {
			roolback();
			ex.getStackTrace();
			throw new CursoDAOException(ex);
		}
		return dominio;
	}

}
