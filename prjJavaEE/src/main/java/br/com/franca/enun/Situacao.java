package br.com.franca.enun;

import java.util.Arrays;

public enum Situacao {
	// Turma, Unidade
	DESATIVADA(0, "Desativada"),

	ATIVA(1, "Ativa"),

	// Aluno, Contrato
	ATIVO(2, "Ativo"),

	DESATIVADO(3, "Desativado"),

	// Matricula
	MATRICULADO(4, "Matriculado"),

	CANCELADA(5, "Cancelada"),

	EM_ACORDO(6, "Em acordo"),

	ENCERRADA(7, "Encerrada"),

	PAGO(8, "Pago"),

	A_VENCER(9, "A vencer"),
	
	INVALIDA(100, "Situação inválida");

	private int chave;
	private String valor;

	private Situacao(int chave, String valor) {
		this.chave = chave;
		this.valor = valor;
	}

	/**
	 * @param Alguma chave válida 0,1,2,3 etc..
	 * @return Uma Situação ou null caso não encontre uma situação existente
	 */
	public Situacao getSituacao(int chave) {
		return Arrays.asList(Situacao.values())
				.parallelStream()
				.filter(e -> e.getChave() == chave)
				.findFirst()
				.orElse(Situacao.INVALIDA);
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
