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

		BigDecimal desconto = BigDecimal.valueOf(0);
		BigDecimal taxaMatricula = BigDecimal.valueOf(0);

		if (contrato.getDescontoCurso() != null)
			desconto = contrato.getValorCurso().multiply(BigDecimal.valueOf(contrato.getDescontoCurso()));

		if (contrato.getTaxaMatricula() != null)
			taxaMatricula = contrato.getTaxaMatricula();

		BigDecimal cursoComDescontoAvista = contrato.getValorCurso().subtract(desconto);

		Calendar proximoVencimento = Calendar.getInstance();

		BigDecimal parcelaDoMaterial = contrato.getValorMaterial()
				.divide(BigDecimal.valueOf(contrato.getQtdParcelasMaterial()), 2, BigDecimal.ROUND_DOWN);

		BigDecimal residualDaParcelaMaterial = contrato.getValorMaterial()
				.subtract(parcelaDoMaterial.multiply(BigDecimal.valueOf(contrato.getQtdParcelasMaterial())));

		// contrato.setResidualDaParcelaDoCurso(BigDecimal.valueOf(0));

		// contrato.setResidualDaParcelaDoMaterial(residualDaParcelaMaterial);

		// parcela.setNumeroDaParcela(1);

		// parcela.setNumeroDaParcelaCurso(0);

		// parcela.setNumeroDaParcelaMaterial(0);

		parcela.setDataVencimento(Calendar.getInstance());

		// parcela.setValorResidualDaParcelaCurso(BigDecimal.valueOf(0));

		parcela.setValorParcelaMaterial(BigDecimal.valueOf(0));

		// parcela.setValorResidualDaParcelaMaterial(BigDecimal.valueOf(0));

		// parcela.setTaxaMatricula(contrato.getTaxaMatricula());

		parcela.setValorParcelaCurso(cursoComDescontoAvista.add(taxaMatricula));

		parcela.setValorTotalParcela(cursoComDescontoAvista.add(taxaMatricula));

		/*
		 * parcela.setValorTotalDaParcela(parcela.getValorDaParcelaDoCurso().add(parcela
		 * .getValorDaParcelaDoMaterial()) .add(parcela.getTaxaMatricula()));
		 */

		parcela.setValorPago(parcela.getValorTotalParcela());

		parcela.setDataPagamento(Calendar.getInstance());

		parcela.setSituacao(SituacaoParcela.PAGO);

		parcelas.add(parcela);

		// proximoVencimento.set(Calendar.DAY_OF_MONTH, contrato.getDiaVencimento());

		int diferenca = Math.abs(contrato.getDiaVencimento() - Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

		if (diferenca >= 20)
			proximoVencimento.add(Calendar.MONTH, 1);

		for (int i = 1; i <= contrato.getQtdParcelasMaterial(); i++) {

			parcela = new Parcela();

			// parcela.setNumeroDaParcela(i + 1);

			// parcela.setNumeroDaParcelaCurso(0);

			// parcela.setNumeroDaParcelaMaterial(i);

			proximoVencimento = Calendar.getInstance();

			proximoVencimento.set(Calendar.DAY_OF_MONTH, contrato.getDiaVencimento());

			proximoVencimento.add(Calendar.MONTH, i);

			parcela.setDataVencimento(proximoVencimento);

			parcela.setValorParcelaCurso((BigDecimal.valueOf(0)));

			// parcela.setValorResidualDaParcelaCurso((BigDecimal.valueOf(0)));

			// parcela.setTaxaMatricula(BigDecimal.valueOf(0));

			parcela.setValorParcelaMaterial(parcelaDoMaterial);

			/*
			 * parcela.setValorDaParcelaDoMaterial(contrato.getValorMaterial()
			 * .divide(BigDecimal.valueOf(contrato.getQtdParcelasMaterial()), 2,
			 * BigDecimal.ROUND_DOWN));
			 */

			// parcela.setValorResidualDaParcelaMaterial(BigDecimal.valueOf(0));

			if (i == contrato.getQtdParcelasMaterial()) {

				parcela.setValorParcelaMaterial(parcelaDoMaterial.add(residualDaParcelaMaterial));

				/*
				 * parcela.setValorResidualDaParcelaMaterial(contrato.getValorMaterial()
				 * .subtract(parcela.getValorDaParcelaDoMaterial()
				 * .multiply(BigDecimal.valueOf(contrato.getQtdParcelasMaterial()))));
				 */

				/*
				 * parcela.setValorDaParcelaDoMaterial( parcela.getValorDaParcelaDoMaterial()
				 * .add(parcela.getValorResidualDaParcelaMaterial()));
				 */
			}

			/*
			 * parcela.setValorTotalDaParcela(
			 * parcela.getValorResidualDaParcelaMaterial().add(parcela.
			 * getValorDaParcelaDoMaterial()));
			 */

			parcela.setValorTotalParcela(parcela.getValorParcelaMaterial());

			parcela.setValorPago(BigDecimal.valueOf(0));

			parcela.setDataPagamento(null);

			parcela.setSituacao(SituacaoParcela.A_VENCER);

			parcelas.add(parcela);
		}
		return parcelas;
		// contrato.setParcelas(parcelas);
	}

}
