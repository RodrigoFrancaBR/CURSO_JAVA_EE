package br.com.franca.enun;

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

	public void setChave(int chave) {
		this.chave = chave;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
