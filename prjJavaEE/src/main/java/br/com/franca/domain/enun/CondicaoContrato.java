package br.com.franca.domain.enun;

import java.util.Arrays;

import br.com.franca.strategy.CursoMaterialAvista;

public enum CondicaoContrato {
	CURSO_AVISTA_MATERIAL_PARCELADO(1, "Curso Àvista e Material Parcelado"),
	CURSO_MATERIAL_AVISTA(2, "Curso e Material Àvista"),
	CURSO_MATERIAL_PARCELADO(3, "Curso e Material Parcelado"),
	CURSO_PARCELADO_MATERIAL_AVISTA(4, "Curso Parcelado e Material Àvista"),
	INVALIDO(100, "Condição de contrato inválido");

	private final int chave;
	private final String valor;

	private CondicaoContrato(int chave, String valor) {
		this.chave = chave;
		this.valor = valor;
	}

	public CondicaoContrato getCondicaoContrato(Integer chave) {
		return Arrays.asList(CondicaoContrato.values()).parallelStream().filter(e -> e.getChave() == chave).findFirst()
				.orElse(CondicaoContrato.INVALIDO);
	}
	
	public CondicaoContrato getCondicaoContrato(Integer qtdParcelasCurso, Integer qtdParcelasMaterial) {
		if (qtdParcelasCurso == 1 && qtdParcelasMaterial == 1) {
			return CURSO_MATERIAL_AVISTA;
		}
		return null;
	}
	

	public int getChave() {
		return chave;
	}

	public String getValor() {
		return valor;
	}
}
