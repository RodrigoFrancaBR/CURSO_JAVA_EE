package br.com.franca.domain;

import java.util.List;

public abstract class CondicaoDeContrato {
	public abstract List<Parcela> gerarParcelas(Contrato contrato);
}
