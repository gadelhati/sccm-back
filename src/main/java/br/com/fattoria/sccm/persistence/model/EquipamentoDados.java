package br.com.fattoria.sccm.persistence.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "equipamento_dados")
public class EquipamentoDados implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EquipamentoDadosPK equipamentoDadosPK;

	@ManyToOne
	@JoinColumn(name = "fk_equipamento", insertable = false, updatable = false)
	private Equipamento equipamento;

	@ManyToOne
	@JoinColumn(name = "fk_tipo_dado", insertable = false, updatable = false)
	private TipoDado tipoDado;

	public EquipamentoDados() {
		super();
	}

	public EquipamentoDados(EquipamentoDadosPK equipamentoDadosPK) {
		super();
		this.equipamentoDadosPK = equipamentoDadosPK;
	}
	
	public EquipamentoDadosPK getEquipamentoDadosPK() {
		return equipamentoDadosPK;
	}

	public void setEquipamentoDadosPK(EquipamentoDadosPK equipamentoDadosPK) {
		this.equipamentoDadosPK = equipamentoDadosPK;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public TipoDado getTipoDado() {
		return tipoDado;
	}

	public void setTipoDado(TipoDado tipoDado) {
		this.tipoDado = tipoDado;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EquipamentoDados other = (EquipamentoDados) obj;
		if (equipamentoDadosPK == null) {
			if (other.equipamentoDadosPK != null)
				return false;
		} else if (!equipamentoDadosPK.equals(other.equipamentoDadosPK))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((equipamentoDadosPK == null) ? 0 : equipamentoDadosPK.hashCode());
		return result;
	}

	 
}
