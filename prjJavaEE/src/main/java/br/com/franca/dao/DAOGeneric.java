package br.com.franca.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.franca.exceptions.CursoDAOException;

public abstract class DAOGeneric<Dominio> {

	private Class<Dominio> dominio;
	private EntityManager em;

	public DAOGeneric(Class<Dominio> dominio, EntityManager em) {
		this.dominio = dominio;
		this.em = em;
	}

	public void roolback() {
		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
		}
		em.getTransaction().rollback();
	}

	public Dominio fimdById(Long id) throws CursoDAOException {
		try {
			return em.find(dominio, id);		
		} catch (Exception ex) {
			throw new CursoDAOException(ex);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Dominio> findAll() throws CursoDAOException {
		/*
		 * String jpql = "select u from " + dominio.getSimpleName() + " u"; Query query
		 * = em.createQuery(jpql); List<Dominio> resultList = query.getResultList();
		 * return resultList;
		 */
		// return em.createQuery("from " + dominio.getSimpleName()).getResultList();
		try {
			return em.createQuery("select u from " + dominio.getSimpleName() + " u").getResultList();
		} catch (Exception ex) {
			throw new CursoDAOException(ex);
		}
	}

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

	public void delete(Dominio dominio) throws CursoDAOException {
		try {
			em.getTransaction().begin();
			em.merge(dominio);
			// em.remove(dominio);
			em.getTransaction().commit();
		} catch (Exception ex) {
			roolback();
			ex.getStackTrace();
			throw new CursoDAOException(ex);
		}
	}

}
