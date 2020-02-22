package br.com.franca.business;

import java.util.List;

import br.com.franca.dao.implement.ContratoDAO;
import br.com.franca.domain.Contrato;

public class ContratoBusiness {

	private ContratoDAO dao;

	public ContratoBusiness() {
		super();
		this.dao = new ContratoDAO();
	}

	public List<Contrato> findAll() {
		// this.dao.findAll();
		return null;
	}

	public Contrato find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Contrato insert(Contrato contrato) {
		// TODO Auto-generated method stub
		return null;
	}

	public Contrato update(Contrato contrato) {
		// TODO Auto-generated method stub
		return null;
	}

	public Contrato delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
