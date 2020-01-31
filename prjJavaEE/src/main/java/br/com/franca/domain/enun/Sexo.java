package br.com.franca.domain.enun;

import java.util.Arrays;

public enum Sexo {
	MASCULINO(0, "Masculino"), FEMININO(1, "Feminino");

	private Sexo(int chave, String valor) {
		this.chave = chave;
		this.valor = valor;
	}

	private int chave;
	private String valor;

	public Sexo getSexo(int chave) {
		return Arrays.asList(Sexo.values()).parallelStream().findAny().orElse(null);
	}

	public int getChave() {
		return chave;
	}

	public String getValor() {
		return valor;
	}

}
