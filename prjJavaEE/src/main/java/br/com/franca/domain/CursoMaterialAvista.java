package br.com.franca.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.franca.domain.enun.SituacaoParcela;

public class CursoMaterialAvista extends CondicaoDeContrato {

	@Override
	public List<Parcela> calculaParcelas(Contrato contrato) {

		Parcela parcela = new Parcela();
		List<Parcela> parcelas = new ArrayList<Parcela>();

		// parcela.setNumeroDaParcela(1);

		// uma cobrança de curso

		// parcela.setNumeroDaParcelaCurso(1);

		// uma cobrança de material
		// parcela.setNumeroDaParcelaMaterial(1);

		parcela.setDataVencimento(Calendar.getInstance());

		BigDecimal desconto = contrato.getValorCurso().multiply(BigDecimal.valueOf(contrato.getDescontoCurso()));

		parcela.setValorParcelaCurso(contrato.getValorCurso().subtract(desconto));

		parcela.setValorResidualParcelaCurso(BigDecimal.valueOf(0));

		parcela.setValorParcelaMaterial(contrato.getValorMaterial());

		parcela.setValorResidualParcelaMaterial(BigDecimal.valueOf(0));

		parcela.setValorTotalParcela(
				parcela.getValorParcelaCurso().add(parcela.getValorParcelaMaterial()).add(contrato.getTaxaMatricula()));

		parcela.setValorPago(parcela.getValorTotalParcela());

		parcela.setDataPagamento(Calendar.getInstance());

		parcela.setSituacao(SituacaoParcela.PAGO);

		parcela.setContrato(contrato);

		parcelas.add(parcela);

		return parcelas;

	}

}
