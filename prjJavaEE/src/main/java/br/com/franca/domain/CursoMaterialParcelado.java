package br.com.franca.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.franca.domain.enun.SituacaoParcela;

public class CursoMaterialParcelado extends CondicaoDeContrato {

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

		BigDecimal parcelaDoMaterial = contrato.getValorMaterial()
				.divide(BigDecimal.valueOf(contrato.getQtdParcelasMaterial()), 2, BigDecimal.ROUND_DOWN);

		BigDecimal residualDaParcelaMaterial = contrato.getValorMaterial()
				.subtract(parcelaDoMaterial.multiply(BigDecimal.valueOf(contrato.getQtdParcelasMaterial())));

		parcela.setDataVencimento(Calendar.getInstance());

		Calendar proximoVencimento = Calendar.getInstance();

		// parcela.setNumeroDaParcela(1);

		// primeira cobrança do curso

		// sem cobrança de material na primeira parcela

		// sem residuo na primeira parcela

		// sem cobrança de material na primeira parcela
		parcela.setValorParcelaMaterial(BigDecimal.valueOf(0));

		// sem residuo na primeira parcela

		parcela.setValorParcelaCurso(cursoComDescontoParcelado.add(contrato.getTaxaMatricula()));

		parcela.setValorTotalParcela(parcela.getValorParcelaCurso());

		parcela.setValorPago(parcela.getValorTotalParcela());

		parcela.setDataPagamento(Calendar.getInstance());

		parcela.setSituacao(SituacaoParcela.PAGO);

		// primeira parcela paga no ato
		parcela.setDataVencimento(Calendar.getInstance());

		parcelas.add(parcela);

		int diferenca = Math.abs(contrato.getDiaVencimento() - Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

		if (diferenca >= 20)
			proximoVencimento.add(Calendar.MONTH, 1);

		for (int i = 2; (i <= contrato.getQtdParcelasCurso() || i <= contrato.getQtdParcelasMaterial() + 1); i++) {

			parcela = new Parcela();
			// segunda parcela

			proximoVencimento = Calendar.getInstance();

			proximoVencimento.set(Calendar.DAY_OF_MONTH, contrato.getDiaVencimento());

			proximoVencimento.add(Calendar.MONTH, i - 1);

			parcela.setDataVencimento(proximoVencimento);

			// garante que inicie com zero
			parcela.setValorParcelaCurso(BigDecimal.valueOf(0));
			parcela.setValorParcelaMaterial(BigDecimal.valueOf(0));
			// sem taxa de matricula

			// verifico se tem parcelas de curso
			if (i <= contrato.getQtdParcelasCurso()) {

				// verificar se eh a ultima parcela do curso
				if (i == contrato.getQtdParcelasCurso()) {
					// valor da parcela com o residual na ultima parcela
					parcela.setValorParcelaCurso(cursoComDescontoParcelado.add(residualDaParcelaCurso));
				} else {
					// segunda parcela do curso
					parcela.setValorParcelaCurso(cursoComDescontoParcelado);
				}

			}

			// verifico se tem parcelas de Material
			if (i <= contrato.getQtdParcelasMaterial() + 1) {

				// verifico se eh a ultima do material
				if (i == contrato.getQtdParcelasMaterial() + 1) {

					// valor da parcela com o residual na ultima parcela
					parcela.setValorParcelaMaterial(parcelaDoMaterial.add(residualDaParcelaMaterial));
				} else {
					// pois a primeira parcela nunca entra a parcela do material apenas a do curso
					parcela.setValorParcelaMaterial(parcelaDoMaterial);
				}

			}

			parcela.setValorTotalParcela(parcela.getValorParcelaCurso().add(parcela.getValorParcelaMaterial()));

			parcela.setValorPago(BigDecimal.valueOf(0));

			parcela.setDataPagamento(null);

			parcela.setSituacao(SituacaoParcela.A_VENCER);

			parcelas.add(parcela);
		}

		return parcelas;
	}

}
