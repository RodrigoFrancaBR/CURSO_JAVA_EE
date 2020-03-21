package br.com.franca.business;

public abstract class BusinessGeneric<Dominio> {

	private Class<Dominio> dominio;

	public BusinessGeneric(Class<Dominio> dominio) {
		this.dominio = dominio;
	}

	protected boolean domainIsNull(Dominio dominio) {
		return dominio == null ? true : false;
	}

	protected boolean idIsNull(Long id) {
		return id == null ? true : false;
	}

	protected boolean nomeIsInvalid(String nome) {
		return nome == null || nome.trim().equals("") ? true : false;
	}

	protected boolean enderecoIsInvalid(String endereco) {
		return endereco == null || endereco.trim().equals("") ? true : false;
	}

}
