package br.com.franca.dao.interfaces;

import java.util.List;

import br.com.franca.dao.exceptions.CursoDAOException;

public interface CRUDI<Dominio, Id> {

	public List<Dominio> findAll() throws CursoDAOException;

	public Dominio find(Id id) throws CursoDAOException;

	public Dominio save(Dominio dominio) throws CursoDAOException;

	public Dominio update(Dominio dominio) throws CursoDAOException;

	public Dominio delete(Dominio id) throws CursoDAOException;

}
