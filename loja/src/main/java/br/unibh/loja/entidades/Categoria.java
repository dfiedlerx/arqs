package br.unibh.loja.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "tb_categoria", uniqueConstraints = { @UniqueConstraint(columnNames = { "descricao" }) })

@NamedQueries({
		@NamedQuery(name = "Categoria.findByName", query = "select c from Categoria c where c.descricao like :descricao") })

public class Categoria implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(min = 1, max = 100)
	@Pattern(regexp = "[A-zÀ-ú.-/' ]*", message = "Caracteres permitidos: letras, espaços, barra, traços ,ponto e aspas simples")
	@Column(length = 100, nullable = false)
	private String descricao;

	@Version
	private Long version;

	public Categoria() {
		super();
	}

	public Categoria(Long id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}

	// Métodos get e set
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	// Métodos hashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	// Métodos equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	// Método toString
	@Override
	public String toString() {
		return "Categoria [id=" + id + ", descricao=" + descricao + "]";
	}
}
