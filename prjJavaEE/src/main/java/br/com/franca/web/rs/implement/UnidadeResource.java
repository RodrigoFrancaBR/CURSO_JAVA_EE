package br.com.franca.web.rs.implement;

import java.util.List;

import br.com.franca.business.implement.UnidadeBusiness;
import br.com.franca.dao.implement.UnidadeDAOImp;
import br.com.franca.domain.Unidade;
import br.com.franca.web.api.UnidadeAPI;

public class UnidadeResource extends ResourceGeneric<Unidade, Long, UnidadeDAOImp, UnidadeBusiness> implements UnidadeAPI{
	
	public UnidadeResource() {
		super();
		this.business = new UnidadeBusiness();
	}

	@Override
	public List<Unidade> buscarTodas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Unidade buscarPor(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Unidade inserir(Unidade unidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Unidade atualizar(Unidade unidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Unidade remover(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
