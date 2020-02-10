package br.com.franca.business.implement;

import java.util.List;

import br.com.franca.crud.interfaces.CRUDI;
import br.com.franca.dao.implement.DAOGeneric;

public abstract class BusinessGeneric<Dominio, Id, DAO extends DAOGeneric<Dominio, Id>> implements CRUDI<Dominio, Id> {

	protected Class<Dominio> dominio;
	protected DAOGeneric<Dominio, Id> dao;
	private String mensagem = "";

	public DAOGeneric<Dominio, Id> getDao() {
		return dao;
	}

	public BusinessGeneric() {
	}

	public List<Dominio> findAll() {
		return this.dao.findAll();
	}

	@Override
	public Dominio find(Id id) {
		return this.dao.find(id);
	}

	@Override
	public Dominio save(Dominio dominio) {
		return this.dao.save(dominio);

	}

	@Override
	public Dominio update(Dominio dominio) {
		return this.dao.update(dominio);
	}

	@Override
	public Id delete(Id id) {
		return this.dao.delete(id);
	}

}
