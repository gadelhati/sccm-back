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
@Table(name = "equipamento_dados")
public class EquipamentoDados implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EquipamentoDadosPK equipamentoDadosPK;
	
	@ManyToOne
	@JoinColumn(name = "fk_equipamento", insertable = false, updatable = false)
	private Equipamento equipamento;
	
	@ManyToOne
	@JoinColumn(name = "fk_tipo_dados", insertable = false, updatable = false)
	private TipoDado tipoDado;


}
