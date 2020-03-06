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

import br.com.franca.domain.enun.FormaPagamento;
import br.com.franca.domain.enun.SituacaoMatricula;

@Table(name = "TB_CONTRATO")
@Entity
public class Contrato implements BaseEntity<Long>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8311550580079368922L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "tx_matri")
	private BigDecimal taxaMatricula = new BigDecimal(0);

	@Column(name = "vlr_curso")
	private BigDecimal valorCurso;

	@Column(name = "desc_curso")
	private Double descontoCurso;
	
	@Transient
	// @Column(name = "qtd_parc_curso")
	private Integer qtdParcelasCurso;
	
	@Transient
	// @Column(name = "qtd_parc_mate")
	private Integer qtdParcelasMaterial;

	@Column(name = "vlr_mate")
	private BigDecimal valorMaterial;

	@Enumerated(EnumType.STRING)
	@Column(name = "forma_pg")
	private FormaPagamento formaPagamento;

	@Column(name = "dia_venc")
	private Integer diaVencimento;

	private String matricula;

	@Temporal(TemporalType.DATE)
	@Column(name = "dt_matri")
	private Calendar dataMatricula = Calendar.getInstance();

	@Enumerated(EnumType.STRING)
	@Column(name = "sit_matric")
	SituacaoMatricula situacao;

	@ManyToOne
	@JoinColumn(name = "aluno_id")
	private Aluno aluno;

	@ManyToOne
	@JoinColumn(name = "turma_id")
	private Turma turma;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public BigDecimal getTaxaMatricula() {
		return taxaMatricula;
	}

	public void setTaxaMatricula(BigDecimal taxaMatricula) {
		this.taxaMatricula = taxaMatricula;
	}

	public BigDecimal getValorCurso() {
		return valorCurso;
	}

	public void setValorCurso(BigDecimal valorCurso) {
		this.valorCurso = valorCurso;
	}

	public Double getDescontoCurso() {
		return descontoCurso;
	}

	public void setDescontoCurso(Double descontoCurso) {
		this.descontoCurso = descontoCurso;
	}

	public Integer getQtdParcelasCurso() {
		return qtdParcelasCurso;
	}

	public void setQtdParcelasCurso(Integer qtdParcelasCurso) {
		this.qtdParcelasCurso = qtdParcelasCurso;
	}

	public Integer getQtdParcelasMaterial() {
		return qtdParcelasMaterial;
	}

	public void setQtdParcelasMaterial(Integer qtdParcelasMaterial) {
		this.qtdParcelasMaterial = qtdParcelasMaterial;
	}

	public BigDecimal getValorMaterial() {
		return valorMaterial;
	}

	public void setValorMaterial(BigDecimal valorMaterial) {
		this.valorMaterial = valorMaterial;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Integer getDiaVencimento() {
		return diaVencimento;
	}

	public void setDiaVencimento(Integer diaVencimento) {
		this.diaVencimento = diaVencimento;
	}

	public Calendar getDataMatricula() {
		return dataMatricula;
	}

	public void setDataMatricula(Calendar dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public SituacaoMatricula getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoMatricula situacao) {
		this.situacao = situacao;
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
		Contrato other = (Contrato) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
