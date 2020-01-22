package br.com.franca.web.rs;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import br.com.franca.domain.Unidade;
import br.com.franca.domain.enun.Status;
import br.com.franca.web.api.UnidadeAPI;

@Path("unidade")
public class UnidadeResource implements UnidadeAPI {

	@Override
	public String findAll() {
		// public List<Unidade> findAll() {
		List<Unidade> unidades = new ArrayList<>();
		Unidade u1 = new Unidade(1l, Status.ATIVA, null, "CASCADURA", "ENDEREÇO 1");
		Unidade u2 = new Unidade(2l, Status.ATIVA, null, "CASCADURA", "ENDEREÇO 2");

		unidades.add(u1);
		unidades.add(u2);

		return "unidades";
	}

}
