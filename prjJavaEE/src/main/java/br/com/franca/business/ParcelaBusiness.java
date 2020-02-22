package br.com.franca.business;

import java.util.List;

import br.com.franca.dao.implement.ParcelaDAO;
import br.com.franca.domain.Parcela;

public class ParcelaBusiness {

	private ParcelaDAO dao;

	public ParcelaBusiness() {
		super();
		this.dao = new ParcelaDAO();
	}

	public List<Parcela> findAll() {
		// this.dao.findAll();
		return null;
	}

	public Parcela find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Parcela insert(Parcela parcela) {
		// TODO Auto-generated method stub
		return null;
	}

	public Parcela update(Parcela parcela) {
		// TODO Auto-generated method stub
		return null;
	}

	public Parcela delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
