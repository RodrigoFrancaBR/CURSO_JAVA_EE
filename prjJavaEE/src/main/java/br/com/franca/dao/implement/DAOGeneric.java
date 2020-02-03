package br.com.franca.dao.implement;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.franca.dao.interfaces.DAOI;
import br.com.franca.domain.BaseEntity;
import br.com.franca.util.EntityManagerUtil;
import cedae.metrusweb.dao.BaseDAO;
import cedae.metrusweb.dao.factory.IDAOFactory;

public abstract class DAOGeneric<T extends BaseEntity<PK>, PK> implements DAOI<T, PK> {

	protected EntityManager em;
	protected Class<T> clazz;
	private String mensagem = "";
	private final HashMap<String, DAOGeneric<BaseEntity<PK>, PK>> listDAO ;

	public DAOGeneric() {
		this.em = EntityManagerUtil.getEntityManager();
		this.listDAO = new HashMap<>();
	}
	
	/**
	 * Obtem a instância de uma classe DAO, de acordo com a classe informada.
	 * @param clazz Classe de DAO desejada.
	 * @return Instância da classe de negócio ou null se a classe informada não herdar de BaseDAO, 
	 * ou não tiver um construtor que receba MetrusDAOFactory como parâmetro.
	 */
    public <U extends DAOGeneric<BaseEntity<PK>, PK>> U getDAO(Class<U> clazz) {
    	U retorno = getInstance(clazz);
    	if (retorno == null) {
    		retorno = createInstance(clazz);
    	}
		return retorno;
	}
    
    /**
     * Cria uma instância da classe de negócio.
	 * @param clazz Classe de negócio desejada.
     * @return Instância da classe de negócio ou null
     */
	protected <U extends DAOGeneric<BaseEntity<PK>, PK>> U createInstance(Class<U> clazz) {
		U retorno = null;
		try {
			Constructor<U> construtor = clazz.getDeclaredConstructor(IDAOFactory.class);
			construtor.setAccessible(true);
			retorno = construtor.newInstance(this);
			if (retorno != null) {
				listaDAO.put(clazz.getName(), retorno);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retorno;
	}
    
    /**
     * Busca uma instância da classe de negócio na listagem.
	 * @param clazz Classe de negócio desejada.
     * @return Instância da classe de negócio ou null
     */
	@SuppressWarnings("unchecked")
	protected <U extends DAOGeneric<BaseEntity<PK>, PK>> U getInstance(Class<U> clazz) {
		U retorno = (U)listDAO.get(clazz.getName());
		return retorno;
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
