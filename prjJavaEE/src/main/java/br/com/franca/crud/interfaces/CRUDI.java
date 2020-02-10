package br.com.franca.crud.interfaces;

import java.util.List;

public interface CRUDI<Dominio, Id> {

	public List<Dominio> findAll();

	public Dominio find(Id id);

	public Dominio save(Dominio entity);

	public Dominio update(Dominio entity);

	public Id delete(Id id);

}
