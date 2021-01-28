package br.com.fattoria.sccm.persistence.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "equipamento_dados")
public class EquipamentoDados implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public EquipamentoDados() {}

	public EquipamentoDados(EquipamentoDadosPK equipamentoDadosPK) {
		this.equipamentoDadosPK = equipamentoDadosPK;
	}

	@EmbeddedId
	private EquipamentoDadosPK equipamentoDadosPK;

	public EquipamentoDadosPK getEquipamentoDadosPK() {
		return equipamentoDadosPK;
	}

	public void setEquipamentoDadosPK(EquipamentoDadosPK equipamentoDadosPK) {
		this.equipamentoDadosPK = equipamentoDadosPK;
	}

}
