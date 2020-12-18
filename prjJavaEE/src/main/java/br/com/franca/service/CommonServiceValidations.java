package br.com.franca.service;

import br.com.franca.domain.enun.Status;

public abstract class CommonServiceValidations {

	protected boolean nomeInvalido(String nome) {
		return nome == null || nome.trim().equals("") ? true : false;
	}

	protected boolean enderecoInvalido(String endereco) {
		return endereco == null || endereco.trim().equals("") ? true : false;
	}

	/*protected boolean statusInvalido(Status status) {
		return status == null || status.equals(Status.INVALIDA) ? true : false;
	}*/

}
