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
@Table(name = "midia_diversa_tipo_midia")
public class MidiaDiversaTipoMidia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MidiaDiversaTipoMidiaPK pk;

	private Long quantidade;
	
	@ManyToOne
	@JoinColumn(name = "fk_midia_diversa", insertable = false, updatable = false)
	private MidiaDiversa midiaDiversa;
	
	@ManyToOne
	@JoinColumn(name = "fk_tipo_midia", insertable = false, updatable = false)
	private TipoMidia tipoMidia;


}
