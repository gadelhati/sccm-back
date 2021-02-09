package br.com.fattoria.sccm.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MidiaDiversaTipoMidiaPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "fk_midia_diversa")
	private Long idMidiaDiversa;

	@Column(name = "fk_tipo_midia")
	private Long idTipoMidia;

	public MidiaDiversaTipoMidiaPK() {
		super();
	}

	public MidiaDiversaTipoMidiaPK(Long idMidiaDiversa, Long idTipoMidia) {
		super();
		this.idMidiaDiversa = idMidiaDiversa;
		this.idTipoMidia = idTipoMidia;
	}

	public Long getIdMidiaDiversa() {
		return idMidiaDiversa;
	}

	public void setIdMidiaDiversa(Long idMidiaDiversa) {
		this.idMidiaDiversa = idMidiaDiversa;
	}

	public Long getIdTipoMidia() {
		return idTipoMidia;
	}

	public void setIdTipoMidia(Long idTipoMidia) {
		this.idTipoMidia = idTipoMidia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idMidiaDiversa == null) ? 0 : idMidiaDiversa.hashCode());
		result = prime * result + ((idTipoMidia == null) ? 0 : idTipoMidia.hashCode());
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
		MidiaDiversaTipoMidiaPK other = (MidiaDiversaTipoMidiaPK) obj;
		if (idMidiaDiversa == null) {
			if (other.idMidiaDiversa != null)
				return false;
		} else if (!idMidiaDiversa.equals(other.idMidiaDiversa))
			return false;
		if (idTipoMidia == null) {
			if (other.idTipoMidia != null)
				return false;
		} else if (!idTipoMidia.equals(other.idTipoMidia))
			return false;
		return true;
	}

}
