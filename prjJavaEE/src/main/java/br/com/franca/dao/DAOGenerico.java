package br.com.franca.dao;

import javax.persistence.EntityManager;

import br.com.franca.util.EntityManagerUtil;

public abstract class DAOGenerico<T extends BaseEntity> {

	protected EntityManager em;
	protected Class<T> clazz;

	public DAOGenerico() {
		this.em = EntityManagerUtil.getEntityManager();
	}

	public Class<T> getClazz() {
		return clazz;
	}

	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

	public T find(Long id) {
		return em.find(clazz, id);
	}

}
