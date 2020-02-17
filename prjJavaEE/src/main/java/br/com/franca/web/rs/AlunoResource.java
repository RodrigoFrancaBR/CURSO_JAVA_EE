package br.com.franca.web.rs;

import java.util.List;

import javax.ws.rs.Path;

import br.com.franca.business.AlunoBusiness;
import br.com.franca.domain.Aluno;
import br.com.franca.web.api.AlunoAPI;

@Path("alunos")
public class AlunoResource implements AlunoAPI {

	private AlunoBusiness business;

	public AlunoResource() {
		super();
		// this.business = new AlunoBusiness();
	}

	@Override
	public List<Aluno> findAll() {
		// this.business.findAll();
		return null;
	}

	@Override
	public Aluno find(Long id) {
		return null;
	}

	@Override
	public Aluno insert(Aluno entity) {
		return null;
	}

	@Override
	public Aluno update(Aluno entity) {
		return null;
	}

	@Override
	public Aluno delete(Long id) {
		return null;
	}

}
