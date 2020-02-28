package br.com.franca.domain;

import java.util.List;

import br.com.franca.strategy.CursoAvistaMaterialParcelado;
import br.com.franca.strategy.CursoMaterialAvista;
import br.com.franca.strategy.CursoMaterialParcelado;
import br.com.franca.strategy.CursoParceladoMaterialAvista;

public abstract class CondicaoDeContrato {

	public final static CondicaoDeContrato getCondicaoContrato(Integer qtdParcelasCurso, Integer qtdParcelasMaterial) {
		if (qtdParcelasCurso == 1 && qtdParcelasMaterial == 1)
			return new CursoMaterialAvista();

		if (qtdParcelasCurso == 1 && qtdParcelasMaterial >= 2)
			return new CursoAvistaMaterialParcelado();

		if (qtdParcelasCurso >= 2 && qtdParcelasMaterial == 1)
			return new CursoParceladoMaterialAvista();

		if (qtdParcelasCurso >= 2 && qtdParcelasMaterial >= 2)
			return new CursoMaterialParcelado();

		return null;
	}

	public abstract List<Parcela> calculaParcelas(Contrato contrato);

}
