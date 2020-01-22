package br.com.franca.domain.enun;

import java.util.Arrays;

public enum CondicaoContrato {
	CURSO_AVISTA_MATERIAL_PARCELADO(1, "Curso Ávista e Material Parcelado"),
	CURSO_MATERIAL_AVISTA(2, "Curso e Material Ávista"), CURSO_MATERIAL_PARCELADO(3, "Curso e Material Parcelado"),
	CURSO_PARCELADO_MATERIAL_AVISTA(4, "Curso Parcelado e Material Ávista");

	private final int chave;
	private final String valor;

	private CondicaoContrato(int chave, String valor) {
		this.chave = chave;
		this.valor = valor;
	}

	public CondicaoContrato getCondicaoContrato(Integer chave) {
		return Arrays.asList(CondicaoContrato.values()).parallelStream().findFirst().orElse(null);
	}

	public int getChave() {
		return chave;
	}

	public String getValor() {
		return valor;
	}
}
