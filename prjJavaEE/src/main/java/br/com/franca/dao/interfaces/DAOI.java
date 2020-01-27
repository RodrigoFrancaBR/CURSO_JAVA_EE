package br.com.franca.dao.interfaces;

import java.util.List;

public interface DAOI<T, PK> {

	public List<T> findAll();

	public T find(PK id);

	public T save(T entity);

	public T update(T entity);

	public PK delete(PK id);

}
