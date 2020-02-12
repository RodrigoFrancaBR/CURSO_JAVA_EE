package br.com.franca.web.rs.implement;

import java.util.List;

import br.com.franca.business.implement.BusinessGeneric;
import br.com.franca.crud.interfaces.CRUDI;
import br.com.franca.dao.implement.DAOGeneric;

public abstract class ResourceGeneric<Dominio, Id, DAO extends DAOGeneric<Dominio, Id>, Business extends BusinessGeneric<Dominio, Id, DAO>>implements CRUDI<Dominio, Id> {
	// public abstract class ResourceGeneric<Dominio, Id, Business extends BusinessGeneric<Dominio, Id, DAOGeneric<Dominio, Id>>>implements CRUDI<Dominio, Id> {

	protected Class<Dominio> dominio;
	protected BusinessGeneric<Dominio, Id, DAO> business;
	private String mensagem = "";

	public ResourceGeneric() {
	}

	@Override
	public List<Dominio> findAll() {
		return this.business.findAll();
	}

	@Override
	public Dominio find(Id id) {
		return this.business.find(id);
	}

	@Override
	public Dominio save(Dominio dominio) {
		return this.business.save(dominio);

	}

	@Override
	public Dominio update(Dominio dominio) {
		return this.business.update(dominio);
	}

	@Override
	public Id delete(Id id) {
		return this.business.delete(id);
	}
}
