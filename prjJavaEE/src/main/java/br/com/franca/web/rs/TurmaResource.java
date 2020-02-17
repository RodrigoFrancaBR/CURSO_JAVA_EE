package br.com.franca.web.rs;

import java.util.List;

import javax.ws.rs.Path;

import br.com.franca.business.TurmaBusiness;
import br.com.franca.domain.Turma;
import br.com.franca.web.api.TurmaAPI;

@Path("turmas")
public class TurmaResource implements TurmaAPI {

	private TurmaBusiness business;

	public TurmaResource() {
		super();
		// this.business = new TurmaBusiness();
	}

	@Override
	public List<Turma> findAll() {
		// this.business.findAll();
		return null;
	}

	@Override
	public Turma find(Long id) {
		return null;
	}

	@Override
	public Turma insert(Turma entity) {
		return null;
	}

	@Override
	public Turma update(Turma entity) {
		return null;
	}

	@Override
	public Turma delete(Long id) {
		return null;
	}

}
