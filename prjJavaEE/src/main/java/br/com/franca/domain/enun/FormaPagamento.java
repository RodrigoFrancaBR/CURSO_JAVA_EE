package br.com.franca.domain.enun;

import java.util.Arrays;

public enum FormaPagamento {
	DINHEIRO(0, "Dinheiro"),

	CHEQUE(1, "Cheque"),

	CARTAO_CREDITO(2, "Cartão de crédito"),

	CARTAO_DEBITO(3, "Cartão de débito"),

	INVALIDA(100, "Forma de Pagamento inválida");

	private final int chave;
	private final String valor;

	private FormaPagamento(int chave, String valor) {
		this.chave = chave;
		this.valor = valor;
	}

	public int getChave() {
		return chave;
	}

	public String getValor() {
		return valor;
	}

	public FormaPagamento getFormaPagamento(Integer chave) {
		return Arrays.asList(FormaPagamento.values()).parallelStream().filter(e -> e.getChave() == chave).findFirst()
				.orElse(FormaPagamento.INVALIDA);
	}
}
