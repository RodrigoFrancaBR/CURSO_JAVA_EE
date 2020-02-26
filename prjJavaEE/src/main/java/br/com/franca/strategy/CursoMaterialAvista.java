package br.com.franca.strategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.franca.domain.Contrato;
import br.com.franca.domain.Parcela;
import br.com.franca.domain.enun.SituacaoParcela;
import br.com.franca.domain.interfaces.CondicaoDeContrato;

public class CursoMaterialAvista implements CondicaoDeContrato {

	private List<Parcela> listaDeParcelas;

	public List<Parcela> getListaDeParcelas() {
		return listaDeParcelas;
	}

	public void setListaDeParcelas(List<Parcela> listaDeParcelas) {
		this.listaDeParcelas = listaDeParcelas;
	}

	public CursoMaterialAvista(Contrato contrato) {
		List<Parcela> listaDeParcelas = this.calculaParcelas(contrato);
		this.setListaDeParcelas(listaDeParcelas);
	}

	public CursoMaterialAvista() {
	}

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

		parcelas.add(parcela);

		return parcelas;

	}

}
