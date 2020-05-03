package br.com.franca.service;

import br.com.franca.domain.enun.Status;

public abstract class CommonServiceValidations {

	protected boolean nomeIsInvalid(String nome) {
		return nome == null || nome.trim().equals("") ? true : false;
	}

	protected boolean enderecoIsInvalid(String endereco) {
		return endereco == null || endereco.trim().equals("") ? true : false;
	}
	
	protected boolean statusInvalido(Status status) {		
		return status == null || status.equals(Status.INVALIDA) ? true : false;
	}
}
