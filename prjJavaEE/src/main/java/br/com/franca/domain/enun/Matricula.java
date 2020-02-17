package br.com.franca.domain.enun;

import java.util.Arrays;

public enum Matricula {
	// Usada para Matriculas

	CANCELADA(0, "Cancelada"), ATIVA(1, "Ativa"), EM_ACORDO(2, "Em acordo"), ENCERRADA(3, "Encerrada"),
	INVALIDA(100, "Situação inválida");

	private int chave;
	private String valor;

	private Matricula(int chave, String valor) {
		this.chave = chave;
		this.valor = valor;
	}

	/**
	 * @param Alguma chave v�lida 0,1,2,3 etc..
	 * @return Uma Matricula ou null caso n�o encontre uma matr�cula existente
	 */
	public Matricula getMatricula(int chave) {
		return Arrays.asList(Matricula.values()).parallelStream().filter(e -> e.getChave() == chave).findFirst()
				.orElse(Matricula.INVALIDA);
	}

	public int getChave() {
		return chave;
	}

	public String getValor() {
		return valor;
	}

}
