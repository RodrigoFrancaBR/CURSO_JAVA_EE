package br.com.franca.web.rs;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Path;

import br.com.franca.domain.Unidade;
import br.com.franca.domain.enun.Status;
import br.com.franca.web.api.UnidadeAPI;

@Path("unidade")
public class UnidadeResource implements UnidadeAPI {
	private List<Unidade> unidades = new ArrayList<>();

	@PostConstruct
	public void init() {

		Unidade u1 = new Unidade(1l, Status.ATIVA, null, "CASCADURA", "ENDEREÇO 1");
		Unidade u2 = new Unidade(2l, Status.ATIVA, null, "CASCADURA", "ENDEREÇO 2");

		unidades.add(u1);
		unidades.add(u2);
	}

	@Override
	public List<Unidade> findAll() {
		return unidades;
	}

	public Unidade find(Long id) {		
		return unidades.parallelStream()
		.filter(e -> e.getId().equals(id))
		.findFirst()
		.map((e) -> {
			return new Unidade(e.getId(), e.getStatus(), e.getTurmas(), e.getNome(), e.getEndereco());
		}).orElse(null);
		// return null;
	}

	@Override
	public Unidade update(Unidade unidade) {
		unidade.setStatus(Status.INVALIDA);
		Unidade u = unidade;
		return u;
	}

	@Override
	public Unidade delete(Long id) {
		return unidades.parallelStream()
				.filter(e -> e.getId().equals(id))
				.findFirst()
				.map((e) -> {
			return new Unidade(e.getId(), Status.DESATIVADA, e.getTurmas(), e.getNome(), e.getEndereco());
		}).orElse(null);
	}

	@Override
	public Unidade insert(Unidade unidade) {
		unidade.setStatus(Status.ATIVA);
		return unidade;
	}
	
}
