package br.com.franca.domain.enun;

import java.util.Arrays;

public enum SituacaoAluno {
	// Usado para Alunos
	INATIVO(0, "Inativo"), ATIVO(1, "Ativo"), INVALIDA(100, "Situação inválida");

	private int chave;
	private String valor;

	private SituacaoAluno(int chave, String valor) {
		this.chave = chave;
		this.valor = valor;
	}

	/**
	 * @param Alguma chave válida 0,1,2,3 etc..
	 * @return Uma Situação ou null caso não encontre uma situação existente
	 */
	public SituacaoAluno getSituacao(int chave) {
		return Arrays.asList(SituacaoAluno.values()).parallelStream().filter(e -> e.getChave() == chave).findFirst()
				.orElse(SituacaoAluno.INVALIDA);
	}

	public int getChave() {
		return chave;
	}

	public String getValor() {
		return valor;
	}

}
