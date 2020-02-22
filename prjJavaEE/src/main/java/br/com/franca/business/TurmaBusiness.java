package br.com.franca.business;

import java.util.List;

import br.com.franca.dao.implement.TurmaDAO;
import br.com.franca.domain.Turma;

public class TurmaBusiness {

	private TurmaDAO dao;

	public TurmaBusiness() {
		super();
		this.dao = new TurmaDAO();
	}

	public List<Turma> findAll() {
		// this.dao.findAll();
		return null;
	}

	public Turma find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Turma insert(Turma turma) {
		// TODO Auto-generated method stub
		return null;
	}

	public Turma update(Turma turma) {
		// TODO Auto-generated method stub
		return null;
	}

	public Turma delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
