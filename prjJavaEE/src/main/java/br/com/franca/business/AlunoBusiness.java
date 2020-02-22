package br.com.franca.business;

import java.util.List;

import br.com.franca.dao.implement.AlunoDAO;
import br.com.franca.domain.Aluno;

public class AlunoBusiness {

	private AlunoDAO dao;

	public AlunoBusiness() {
		super();
		this.dao = new AlunoDAO();
	}
	
	public List<Aluno> findAll() {
		// this.dao.findAll();
		return null;
	}

	public Aluno find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Aluno insert(Aluno aluno) {
		// TODO Auto-generated method stub
		return null;
	}

	public Aluno update(Aluno aluno) {
		// TODO Auto-generated method stub
		return null;
	}

	public Aluno delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
