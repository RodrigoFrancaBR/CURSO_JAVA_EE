package br.com.franca.dao.implement;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.franca.dao.interfaces.DAOI;
import br.com.franca.domain.BaseEntity;
import br.com.franca.util.EntityManagerUtil;

public abstract class DAOGenerico<T extends BaseEntity<PK>, PK> implements DAOI<T, PK> {

	protected EntityManager em;
	protected Class<T> clazz;
	private String mensagem = "";

	public DAOGenerico() {
		this.em = EntityManagerUtil.getEntityManager();
	}

	public Class<T> getClazz() {
		return clazz;
	}

	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

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
	public T find(PK pk) {
		return em.find(clazz, pk);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		return em.createQuery("from " + clazz.getSimpleName()).getResultList();
	}

	@SuppressWarnings("finally")
	@Override
	public T save(T entity) {
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
			this.setMensagem("Entidade persistida: " + entity.getId() + " com sucesso!");
			System.out.println(this.getMensagem());
		} catch (Exception e) {
			roolback();
			this.setMensagem("Ocorreu um erro ao tentar persistir a Entidade: " + entity.getId() + " .");
			System.out.println(this.getMensagem());
			e.getStackTrace();
		} finally {
			return entity;
		}
	}

	@SuppressWarnings("finally")
	@Override
	public T update(T entity) {
		try {
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
			this.setMensagem("Entidade modificada: " + entity.getId() + " com sucesso!");
		} catch (Exception e) {
			roolback();
			this.setMensagem("Ocorreu um erro ao tentar modificar a Entidade: " + entity.getId() + " .");
			e.getStackTrace();
		} finally {
			return entity;
		}
	}

	@SuppressWarnings("finally")
	@Override
	public PK delete(PK id) {
		try {
			em.getTransaction().begin();
			em.remove(id);
			em.getTransaction().commit();
			this.setMensagem("Entidade removida: " + id + " com sucesso!");
		} catch (Exception e) {
			roolback();
			this.setMensagem("Ocorreu um erro ao tentar remover a Entidade: " + id + " .");
			e.getStackTrace();
		} finally {
			return id;
		}
	}

}
