package br.com.franca.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.franca.domain.enun.SituacaoParcela;

@Table(name = "TB_PARCELA")
@Entity
public class Parcela implements BaseEntity<Long>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5762722499351113021L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "dt_venc")
	private Calendar dataVencimento = Calendar.getInstance();

	@Column(name = "vlr_pg")
	private BigDecimal valorPago;

	@Temporal(TemporalType.DATE)
	@Column(name = "dt_pg")
	private Calendar dataPagamento = Calendar.getInstance();

	@Column(name = "vlr_parc_curso")
	private BigDecimal valorParcelaCurso;

	@Column(name = "vlr_parc_mate")
	private BigDecimal valorParcelaMaterial;

	@Column(name = "vlr_tot_parc")
	private BigDecimal valorTotalParcela;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "sit_parc")
	private SituacaoParcela situacao;

	@Transient
	private BigDecimal valorResidualParcelaCurso;

	@Transient
	private BigDecimal valorResidualParcelaMaterial;

	@ManyToOne
	@JoinColumn(name = "contrato_id")
	private Contrato contrato;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public Calendar getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Calendar dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public Calendar getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Calendar dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public BigDecimal getValorParcelaCurso() {
		return valorParcelaCurso;
	}

	public void setValorParcelaCurso(BigDecimal valorParcelaCurso) {
		this.valorParcelaCurso = valorParcelaCurso;
	}

	public BigDecimal getValorParcelaMaterial() {
		return valorParcelaMaterial;
	}

	public void setValorParcelaMaterial(BigDecimal valorParcelaMaterial) {
		this.valorParcelaMaterial = valorParcelaMaterial;
	}

	public BigDecimal getValorTotalParcela() {
		return valorTotalParcela;
	}

	public void setValorTotalParcela(BigDecimal valorTotalParcela) {
		this.valorTotalParcela = valorTotalParcela;
	}

	public SituacaoParcela getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoParcela situacao) {
		this.situacao = situacao;
	}

	public BigDecimal getValorResidualParcelaCurso() {
		return valorResidualParcelaCurso;
	}

	public void setValorResidualParcelaCurso(BigDecimal valorResidualParcelaCurso) {
		this.valorResidualParcelaCurso = valorResidualParcelaCurso;
	}

	public BigDecimal getValorResidualParcelaMaterial() {
		return valorResidualParcelaMaterial;
	}

	public void setValorResidualParcelaMaterial(BigDecimal valorResidualParcelaMaterial) {
		this.valorResidualParcelaMaterial = valorResidualParcelaMaterial;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parcela other = (Parcela) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
