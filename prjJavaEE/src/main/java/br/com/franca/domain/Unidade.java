package br.com.franca.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.franca.domain.enun.Status;

@Entity
//definindo um index name unique: "nome_endereco_UK" para as colunas: nome, endereco
@Table(name = "tb_unidade", uniqueConstraints = @UniqueConstraint(columnNames = { "nome",
		"endereco" }, name = "nome_endereco_UK"))
public class Unidade implements BaseEntity<Long>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8674768342761231956L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.ORDINAL)
	private Status status;

	@OneToMany(mappedBy = "unidade")
	private List<Turma> turmas = new ArrayList<Turma>();

	private String nome;
	private String endereco;

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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public Unidade() {
	}

	public Unidade(Long id, Status status, String nome, String endereco, List<Turma> turmas) {
		this.id = id;
		this.status = status;
		this.nome = nome;
		this.endereco = endereco;
		this.turmas = turmas;
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
		Unidade other = (Unidade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Unidade [id=" + id + ", status=" + status + ", turmas=" + turmas + ", nome=" + nome + ", endereco="
				+ endereco + "]";
	}

}
