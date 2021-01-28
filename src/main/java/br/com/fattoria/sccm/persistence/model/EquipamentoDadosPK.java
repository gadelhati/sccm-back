package br.com.fattoria.sccm.persistence.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class EquipamentoDadosPK implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public EquipamentoDadosPK() {}

	public EquipamentoDadosPK(Equipamento equipamento, TipoDado tipoDado) {
		this.equipamento = equipamento;
		this.tipoDado = tipoDado;
	}
	
	@ManyToOne
	@JoinColumn(name = "fk_equipamento")
	private Equipamento equipamento;
	
	@ManyToOne
	@JoinColumn(name = "fk_tipo_dados")
	private TipoDado tipoDado;
	
	
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
	
	

}
