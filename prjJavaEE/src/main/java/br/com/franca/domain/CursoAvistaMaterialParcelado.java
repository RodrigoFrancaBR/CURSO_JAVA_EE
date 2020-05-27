package br.com.franca.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.franca.domain.enun.SituacaoParcela;

public class CursoAvistaMaterialParcelado extends CondicaoDeContrato {

	@Override
	public List<Parcela> calculaParcelas(Contrato contrato) {

		Parcela parcela = new Parcela();

		List<Parcela> parcelas = new ArrayList<Parcela>();

		BigDecimal desconto = contrato.getValorCurso().multiply(BigDecimal.valueOf(contrato.getDescontoCurso()));

		BigDecimal cursoComDescontoAvista = contrato.getValorCurso().subtract(desconto);

		BigDecimal parcelaDoMaterial = contrato.getValorMaterial()
				.divide(BigDecimal.valueOf(contrato.getQtdParcelasMaterial()), 2, BigDecimal.ROUND_DOWN);

		BigDecimal residualDaParcelaMaterial = contrato.getValorMaterial()
				.subtract(parcelaDoMaterial.multiply(BigDecimal.valueOf(contrato.getQtdParcelasMaterial())));

		parcela.setDataVencimento(Calendar.getInstance());

		Calendar proximoVencimento = Calendar.getInstance();

		parcela.setValorParcelaMaterial(BigDecimal.valueOf(0));

		parcela.setValorParcelaCurso(cursoComDescontoAvista.add(contrato.getTaxaMatricula()));

		parcela.setValorTotalParcela(parcela.getValorParcelaCurso());

		parcela.setValorPago(parcela.getValorTotalParcela());

		parcela.setDataPagamento(Calendar.getInstance());

		parcela.setSituacao(SituacaoParcela.PAGO);

		parcelas.add(parcela);

		int diferenca = Math.abs(contrato.getDiaVencimento() - Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

		if (diferenca >= 20)
			proximoVencimento.add(Calendar.MONTH, 1);

		for (int i = 1; i <= contrato.getQtdParcelasMaterial(); i++) {

			parcela = new Parcela();

			proximoVencimento = Calendar.getInstance();

			proximoVencimento.set(Calendar.DAY_OF_MONTH, contrato.getDiaVencimento());

			proximoVencimento.add(Calendar.MONTH, i);

			parcela.setDataVencimento(proximoVencimento);

			parcela.setValorParcelaCurso((BigDecimal.valueOf(0)));

			parcela.setValorParcelaMaterial(parcelaDoMaterial);

			if (i == contrato.getQtdParcelasMaterial())
				parcela.setValorParcelaMaterial(parcelaDoMaterial.add(residualDaParcelaMaterial));

			parcela.setValorTotalParcela(parcela.getValorParcelaMaterial());

			parcela.setValorPago(BigDecimal.valueOf(0));

			parcela.setDataPagamento(null);

			parcela.setSituacao(SituacaoParcela.A_VENCER);

			parcelas.add(parcela);
		}
		return parcelas;
	}

}
