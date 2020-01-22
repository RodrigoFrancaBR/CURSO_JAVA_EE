package br.com.franca.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.franca.util.EntityManagerUtil;

public class CrudDAO<T> implements CrudDAOI<T> {

	protected EntityManager em;

	public CrudDAO() {
		 em = EntityManagerUtil.getEntityManager();
	}	
	
	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T insert(T unidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T update(T unidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
