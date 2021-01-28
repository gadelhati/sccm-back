package br.com.fattoria.sccm.persistence.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "area_conhecimento_equipamento")
public class AreaConhecimentoEquipamento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public AreaConhecimentoEquipamento() {}

	public AreaConhecimentoEquipamento(AreaConhecimentoEquipamentoPK pk) {
		this.pk = pk;
	}

	@EmbeddedId
	private AreaConhecimentoEquipamentoPK pk;

	public AreaConhecimentoEquipamentoPK getPk() {
		return pk;
	}

	public void setPk(AreaConhecimentoEquipamentoPK pk) {
		this.pk = pk;
	}

}
