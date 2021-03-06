package br.com.franca.domain;

import java.util.List;

public abstract class CondicaoDeContrato {

	public final static CondicaoDeContrato getCondicaoContrato(Integer qtdParcelasCurso, Integer qtdParcelasMaterial) {
		CondicaoDeContrato contrato = null;

		if (qtdParcelasCurso == 1 && qtdParcelasMaterial == 1)
			contrato = new CursoMaterialAvista();		

		if (qtdParcelasCurso == 1 && qtdParcelasMaterial >= 2)
			contrato = new CursoAvistaMaterialParcelado();

		if (qtdParcelasCurso >= 2 && qtdParcelasMaterial == 1)
			contrato = new CursoParceladoMaterialAvista();

		if (qtdParcelasCurso >= 2 && qtdParcelasMaterial >= 2)
			contrato = new CursoMaterialParcelado();

		return contrato;
	}

	public abstract List<Parcela> calculaParcelas(Contrato contrato);

}
