package br.com.fattoria.sccm.persistence.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class AreaConhecimentoEquipamentoPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public AreaConhecimentoEquipamentoPK() {}

	public AreaConhecimentoEquipamentoPK(AreaConhecimento areaConhecimento, Equipamento equipamento) {		
		this.areaConhecimento = areaConhecimento;
		this.equipamento = equipamento;
	}

	@ManyToOne
	@JoinColumn(name = "fk_area_conhecimento")
	private AreaConhecimento areaConhecimento;
	
	@ManyToOne
	@JoinColumn(name = "fk_equipamento")
	private Equipamento equipamento;

	public AreaConhecimento getAreaConhecimento() {
		return areaConhecimento;
	}

	public void setAreaConhecimento(AreaConhecimento areaConhecimento) {
		this.areaConhecimento = areaConhecimento;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}
	
	
	

}
