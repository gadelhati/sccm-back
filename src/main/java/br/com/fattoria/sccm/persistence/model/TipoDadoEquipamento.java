package br.com.fattoria.sccm.persistence.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "tipo_dado_equipamento")
public class TipoDadoEquipamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TipoDadoEquipamentoPK pk;

	@ManyToOne
	@JoinColumn(name = "fk_tipo_dado", insertable = false, updatable = false)
	private TipoDado tipoDado;

	@ManyToOne
	@JoinColumn(name = "fk_equipamento", insertable = false, updatable = false)
	private Equipamento equipamento;

	public TipoDadoEquipamento(TipoDadoEquipamentoPK pk) {
		super();
		this.pk = pk;
	}

}
