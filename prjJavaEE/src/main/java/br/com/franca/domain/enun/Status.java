package br.com.franca.domain.enun;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Status {

	// Usado para Turmas e Unidades

	DESATIVADA(0, "Desativada"), ATIVA(1, "Ativa");

	private int chave;
	private String valor;

	private Status(int chave, String valor) {
		this.chave = chave;
		this.valor = valor;
	}

	public static Status getStatus(int chave) {
		return Arrays.asList(Status.values())
				.parallelStream()
				.filter(e -> e.getChave() == chave)
				.collect(Collectors.toList()).get(0);
	}	
	
	public static Status getStatus(String valor) {		
		return Arrays.asList(Status.values())
				.parallelStream()
				.filter(e -> e.getValor().equals(valor))
				.collect(Collectors.toList()).get(0);		
	}


	public int getChave() {
		return chave;
	}

	public String getValor() {
		return valor;
	}

}
