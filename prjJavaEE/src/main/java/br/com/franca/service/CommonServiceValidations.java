package br.com.franca.service;

import br.com.franca.domain.enun.Sexo;
import br.com.franca.domain.enun.SituacaoAluno;
import br.com.franca.domain.enun.Status;

public abstract class CommonServiceValidations {

	protected boolean nomeInvalido(String nome) {
		return nome == null || nome.trim().equals("") ? true : false;
	}

	protected boolean enderecoInvalido(String endereco) {
		return endereco == null || endereco.trim().equals("") ? true : false;
	}

	protected boolean statusInvalido(Status status) {
		return status == null || status.equals(Status.INVALIDA) ? true : false;
	}

	protected boolean cpfInvalido(String cpf) {
		return cpf == null || cpf.trim().equals("") ? true : false;
	}

	protected boolean rgInvalido(String rg) {
		return rg == null || rg.trim().equals("") ? true : false;
	}

	protected boolean sexoInvalido(Sexo sexo) {
		return sexo == null || sexo.equals(Sexo.INVALIDO) ? true : false;
	}

	protected boolean celularInvalido(String celular) {
		return celular == null || celular.trim().equals("") ? true : false;
	}

	protected boolean residencialInvalido(String residencial) {
		return residencial == null || residencial.trim().equals("") ? true : false;
	}

	protected boolean situacaoAluno(SituacaoAluno situacaoAluno) {
		return situacaoAluno == null || situacaoAluno.equals(situacaoAluno.INVALIDA) ? true : false;
	}

}
