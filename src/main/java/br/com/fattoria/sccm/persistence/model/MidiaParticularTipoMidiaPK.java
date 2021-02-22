package br.com.fattoria.sccm.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MidiaParticularTipoMidiaPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "fk_midia_particular")
	private Long idMidiaParticular;

	@Column(name = "fk_tipo_midia")
	private Long idTipoMidia;

	public MidiaParticularTipoMidiaPK() {
		super();
	}

	public MidiaParticularTipoMidiaPK(Long idMidiaDiversa, Long idTipoMidia) {
		super();
		this.idMidiaParticular = idMidiaDiversa;
		this.idTipoMidia = idTipoMidia;
	}

	public Long getIdMidiaDiversa() {
		return idMidiaParticular;
	}

	public void setIdMidiaDiversa(Long idMidiaDiversa) {
		this.idMidiaParticular = idMidiaDiversa;
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
		result = prime * result + ((idMidiaParticular == null) ? 0 : idMidiaParticular.hashCode());
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
		MidiaParticularTipoMidiaPK other = (MidiaParticularTipoMidiaPK) obj;
		if (idMidiaParticular == null) {
			if (other.idMidiaParticular != null)
				return false;
		} else if (!idMidiaParticular.equals(other.idMidiaParticular))
			return false;
		if (idTipoMidia == null) {
			if (other.idTipoMidia != null)
				return false;
		} else if (!idTipoMidia.equals(other.idTipoMidia))
			return false;
		return true;
	}

}
