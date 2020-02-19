package br.com.franca.business;

import java.util.List;

import br.com.franca.dao.implement.UnidadeDAO;
import br.com.franca.domain.Unidade;
import br.com.franca.domain.enun.Status;

public class UnidadeBusiness extends BusinessGeneric<Unidade, Long> {

	private UnidadeDAO dao;

	public UnidadeBusiness() {
		this.dao = new UnidadeDAO();
	}

	public List<Unidade> findAll() {
		return this.dao.findAll();
	}

	public Unidade find(Long id) {
		Unidade unidade;

		if (idIsNull(id)) {
			throw new RuntimeException("ID não pode ser null.");
		}
		return  this.dao.find(id);
		// unidade = this.dao.find(id);

		// return domainIsNull(unidade) ? null : unidade;
	}

	public Unidade insert(Unidade unidade) {

		if (domainIsNull(unidade)) {
			throw new RuntimeException("Unidade não pode ser null.");
		}

		if (nomeIsInvalid(unidade.getNome())) {
			throw new RuntimeException("Nome não pode ser null.");
		}

		if (enderecoIsInvalid(unidade.getEndereco())) {
			throw new RuntimeException("Endereço não pode ser null.");
		}

		unidade.setStatus(Status.ATIVA);

		return this.dao.save(unidade);
	}

	public Unidade update(Unidade unidade) {

		if (domainIsNull(unidade)) {
			throw new RuntimeException("Unidade não pode ser null.");
		}

		if (idIsNull(unidade.getId())) {
			throw new RuntimeException("ID não pode ser null.");
		}

		return unidade = this.dao.update(unidade);
	}

	public Unidade delete(Long id) {
		/*
		 * if (idIsNull(id)) { throw new RuntimeException("ID não pode ser null."); }
		 * 
		 * Unidade unidade = this.dao.find(id);
		 */
		Unidade unidade = this.find(id);
		return domainIsNull(unidade) ? null : this.dao.delete(unidade);
	}

}
