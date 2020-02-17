package br.com.franca.dao.interfaces;

import java.util.List;

public interface CRUDI<Dominio, Id> {

	public List<Dominio> findAll();

	public Dominio find(Id id);

	public Dominio save(Dominio dominio);

	public Dominio update(Dominio dominio);

	public Dominio delete(Dominio id);

}
