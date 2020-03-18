package br.com.franca.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 
 * @author rfranca PADRAO SIGLETON
 */
public class EntityManagerUtil {
	private static EntityManagerFactory emf = null;
	private static EntityManager em = null;

	public static EntityManager getEntityManager() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("cursoPU");
		}

		if (em == null) {
			em = emf.createEntityManager();
		}
		return em;
	}
}
