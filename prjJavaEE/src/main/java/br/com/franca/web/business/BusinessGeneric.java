package br.com.franca.web.business;

import java.util.List;

import br.com.franca.dao.implement.DAOGeneric;
import br.com.franca.dao.interfaces.DAOI;
import br.com.franca.domain.BaseEntity;

public abstract class BusinessGeneric<T extends BaseEntity<PK>, PK> implements DAOI<T, PK> {

	private BusinessFactory factory;
	private DAOGeneric<BaseEntity<PK>, PK> dao;
	
	public BusinessGeneric() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T find(PK id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T save(T entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T update(T entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PK delete(PK id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// Metodo usado no construtor do business para obter outro business 
	protected <U extends BusinessGeneric> U createBusiness(Class<U> clazz) {
		return factory.getBusiness(clazz);
	}

	
	protected <U extends DAOGeneric<BaseEntity<PK>, PK>> U createDAO(Class<U> clazz) {
		return daoFactory.getDAO(clazz);
	}
 
}
