package br.com.franca.dao.implement;

import java.util.List;

import javax.persistence.EntityManager;

import org.eclipse.persistence.annotations.CascadeOnDelete;

import br.com.franca.dao.interfaces.CRUDI;
import br.com.franca.util.EntityManagerUtil;

public class DAOGeneric<Dominio, Id> implements CRUDI<Dominio, Id> {

	public EntityManager em;
	public Class<Dominio> dominio;
	public String mensagem = "";

	public DAOGeneric() {
		this.em = EntityManagerUtil.getEntityManager();
	}
	/*
	 * public Class<Dominio> getDominio() { return dominio; }
	 * 
	 * public void setDominio(Class<Dominio> dominio) { this.dominio = dominio; }
	 * 
	 * public <DAO extends DAOGeneric<Dominio, Id>> DAO getDAO(Class<DAO> dao) { DAO
	 * daoInstance = null; try { Constructor<DAO> construtor =
	 * dao.getDeclaredConstructor(DAOGeneric.class); construtor.setAccessible(true);
	 * daoInstance = construtor.newInstance(this); } catch (Exception e) { } return
	 * daoInstance; }
	 */

	public void roolback() {
		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
		}
		em.getTransaction().rollback();
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public Dominio find(Id id) {
		return em.find(dominio, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dominio> findAll() {
		/*
		 * String jpql = "select u from " + dominio.getSimpleName() + " u"; Query query
		 * = em.createQuery(jpql); List<Dominio> resultList = query.getResultList();
		 * return resultList;
		 */
		// return em.createQuery("from " + dominio.getSimpleName()).getResultList();

		return em.createQuery("select u from " + dominio.getSimpleName() + " u").getResultList();
	}

	@SuppressWarnings("finally")
	@Override
	public Dominio save(Dominio dominio) {
		try {
			em.getTransaction().begin();
			em.persist(dominio);
			em.getTransaction().commit();
			this.setMensagem("Entidade persistida com sucesso!");
			System.out.println(this.getMensagem());
		} catch (Exception e) {
			roolback();
			this.setMensagem("Ocorreu um erro ao tentar persistir a Entidade.");
			System.out.println(this.getMensagem());
			e.getStackTrace();
		} finally {
			return dominio;
		}
	}

	@SuppressWarnings("finally")
	@Override
	public Dominio update(Dominio dominio) {
		try {
			em.getTransaction().begin();
			em.merge(dominio);
			em.getTransaction().commit();
			this.setMensagem("Entidade modificada com sucesso!");
		} catch (Exception e) {
			roolback();
			this.setMensagem("Ocorreu um erro ao tentar modificar a Entidade.");
			e.getStackTrace();
		} finally {
			return dominio;
		}
	}

	@Override
	public Dominio delete(Dominio dominio) {
		try {
			em.getTransaction().begin();
			em.remove(dominio);
			em.getTransaction().commit();
			this.setMensagem("Entidade removida: com sucesso!");
			return dominio;
		} catch (Exception e) {
			roolback();
			this.setMensagem("Ocorreu um erro ao tentar remover a Entidade.");
			e.getStackTrace();
			return null;
		}
	}

}
