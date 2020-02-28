package br.com.franca.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.franca.domain.enun.Status;

@Entity
//definindo um index name unique: "nome_unidade_id_UK" para as colunas: nome, unidade_id
//definindo um index name: FK_tb_turma_tb_unidade_UK para fk unidade_id
@Table(name = "tb_turma", uniqueConstraints = @UniqueConstraint(columnNames = { "nome",
		"unidade_id" }, name = "nome_unidade_id_UK"), indexes = @Index(columnList = "unidade_id", name = "FK_tb_turma_tb_unidade_UK"))
public class Turma implements BaseEntity<Long>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5005319466125595311L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//@JsonBackReference(value = "unidade")
	// @JsonBackReference
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "unidade_id")
	private Unidade unidade;

	// @JsonBackReference(value = "contrato")
	// @JsonBackReference
	@JsonIgnore
	@OneToMany(mappedBy = "turma")
	private Set<Contrato> contratos;

	@Enumerated(EnumType.ORDINAL)
	private Status status;

	private String nome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	@JsonIgnore
	//@JsonBackReference(value = "unidade")
	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	// @JsonBackReference(value = "contrato")
	@JsonIgnore
	public Set<Contrato> getContratos() {
		return contratos;
	}

	public void setContratos(Set<Contrato> contratos) {
		this.contratos = contratos;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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
		Turma other = (Turma) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Turma [id=" + id + ", unidade=" + unidade + ", contratos=" + contratos + ", status=" + status
				+ ", nome=" + nome + "]";
	}

}
