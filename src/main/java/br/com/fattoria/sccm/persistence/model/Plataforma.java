package br.com.fattoria.sccm.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "plataforma")
@SequenceGenerator(name="plataforma_generator", sequenceName="plataforma_seq", allocationSize = 1)
public class Plataforma {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="plataforma_generator")
	private Long id;
	
	private String nome;

	@Column(name = "INDICATIVO_VISUAL")
	private String indicativoVisual;
	
	private String comandante;
	
	@Column(name = "BANDEIRA_EMBARCACAO")
	private String bandeiraEmbarcacao;
	
	@ManyToOne
	@JoinColumn(name = "FK_TIPO_PLATAFORMA")
	private TipoPlataforma tipoPlataforma;

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getIndicativoVisual() {
		return indicativoVisual;
	}

	public String getComandante() {
		return comandante;
	}

	public String getBandeiraEmbarcacao() {
		return bandeiraEmbarcacao;
	}

	public TipoPlataforma getTipoPlataforma() {
		return tipoPlataforma;
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
		Plataforma other = (Plataforma) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Plataforma [id=" + id + ", nome=" + nome + ", indicativoVisual=" + indicativoVisual + ", comandante="
				+ comandante + ", bandeiraEmbarcacao=" + bandeiraEmbarcacao + ", tipoPlataforma=" + tipoPlataforma
				+ "]";
	}

	
}
