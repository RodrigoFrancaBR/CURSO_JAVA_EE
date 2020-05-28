package br.com.franca.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.franca.domain.enun.SituacaoParcela;

public class CursoParceladoMaterialAvista extends CondicaoDeContrato {

	@Override
	public List<Parcela> calculaParcelas(Contrato contrato) {
		Parcela parcela = new Parcela();

		List<Parcela> parcelas = new ArrayList<Parcela>();

		BigDecimal desconto = contrato.getValorCurso().multiply(BigDecimal.valueOf(contrato.getDescontoCurso()));

		BigDecimal cursoComDesconto = contrato.getValorCurso().subtract(desconto);

		BigDecimal cursoComDescontoParcelado = cursoComDesconto
				.divide(BigDecimal.valueOf(contrato.getQtdParcelasCurso()), 2, BigDecimal.ROUND_DOWN);

		BigDecimal residualDaParcelaCurso = cursoComDesconto
				.subtract(cursoComDescontoParcelado.multiply(BigDecimal.valueOf(contrato.getQtdParcelasCurso())));

		parcela.setDataVencimento(Calendar.getInstance());

		Calendar proximoVencimento = Calendar.getInstance();

		// material avista

		// primeira parcela

		// primeira cobrança do curso

		// apenas uma cobrança de material pois foi avista

		parcela.setDataVencimento(Calendar.getInstance());

		parcela.setValorParcelaCurso(cursoComDescontoParcelado);

		// unica parcela pois foi avista
		parcela.setValorParcelaMaterial(contrato.getValorMaterial());

		parcela.setValorTotalParcela(
				cursoComDescontoParcelado.add(contrato.getTaxaMatricula()).add(parcela.getValorParcelaMaterial()));

		parcela.setValorPago(parcela.getValorTotalParcela());

		parcela.setDataPagamento(Calendar.getInstance());

		parcela.setSituacao(SituacaoParcela.PAGO);

		parcelas.add(parcela);

		for (int i = 2; i <= contrato.getQtdParcelasCurso(); i++) {

			parcela = new Parcela();
			// segunda parcela

			// segunda parcela do curso

			// nenhuma cobrança de material pois foi avista

			proximoVencimento = Calendar.getInstance();

			proximoVencimento.set(Calendar.DAY_OF_MONTH, contrato.getDiaVencimento());

			proximoVencimento.add(Calendar.MONTH, i - 1);

			parcela.setDataVencimento(proximoVencimento);

			parcela.setValorParcelaCurso(cursoComDescontoParcelado);

			parcela.setValorParcelaMaterial(BigDecimal.valueOf(0));

			// ultima parcela do curo
			if (i == contrato.getQtdParcelasCurso()) {
				parcela.setValorParcelaCurso(cursoComDescontoParcelado.add(residualDaParcelaCurso));
			}

			parcela.setValorTotalParcela(parcela.getValorParcelaCurso());

			parcela.setValorPago(BigDecimal.valueOf(0));

			parcela.setDataPagamento(null);

			parcela.setSituacao(SituacaoParcela.A_VENCER);

			parcelas.add(parcela);
		}
		return parcelas;
	}

}
