package br.com.fattoria.sccm.persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

//@Data
//@EqualsAndHashCode
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name = "tipo_midia")
@SequenceGenerator(name="tipo_midia_generator", sequenceName="tipo_midia_seq", allocationSize = 1)
public class TipoMidia {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tipo_midia_generator")
	private Long id;

	private String descricao;

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
		TipoMidia other = (TipoMidia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TipoMidia [id=" + id + ", descricao=" + descricao + "]";
	}

	
}
