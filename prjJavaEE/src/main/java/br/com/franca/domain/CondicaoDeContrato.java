package br.com.franca.domain;

import java.util.List;

import br.com.franca.strategy.CursoAvistaMaterialParcelado;
import br.com.franca.strategy.CursoMaterialAvista;
import br.com.franca.strategy.CursoMaterialParcelado;
import br.com.franca.strategy.CursoParceladoMaterialAvista;

public abstract class CondicaoDeContrato {

	public final static CondicaoDeContrato getCondicaoContrato(Integer qtdParcelasCurso, Integer qtdParcelasMaterial) {
		CondicaoDeContrato contrato = null;

		if (qtdParcelasCurso == 1 && qtdParcelasMaterial == 1)
			// return new CursoMaterialAvista();
			contrato = new CursoMaterialAvista();
		;

		if (qtdParcelasCurso == 1 && qtdParcelasMaterial >= 2)
			contrato = new CursoAvistaMaterialParcelado();
		// return new CursoAvistaMaterialParcelado();

		if (qtdParcelasCurso >= 2 && qtdParcelasMaterial == 1)
			contrato = new CursoParceladoMaterialAvista();
		// return new CursoParceladoMaterialAvista();

		if (qtdParcelasCurso >= 2 && qtdParcelasMaterial >= 2)
			contrato = new CursoMaterialParcelado();
		// return new CursoMaterialParcelado();

		return contrato;
	}

	public abstract List<Parcela> calculaParcelas(Contrato contrato);

}
