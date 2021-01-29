package br.com.fattoria.sccm.persistence.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "area_conhecimento_equipamento")
public class AreaConhecimentoEquipamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AreaConhecimentoEquipamentoPK pk;

	@ManyToOne
	@JoinColumn(name = "fk_area_conhecimento", insertable = false, updatable = false)
	private AreaConhecimento areaConhecimento;
	
	@ManyToOne
	@JoinColumn(name = "fk_equipamento", insertable = false, updatable = false)
	private Equipamento equipamento;
	
	
}
