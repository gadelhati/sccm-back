package br.com.fattoria.sccm.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TipoDadoEquipamentoPK implements Serializable {

	private static final long serialVersionUID = 1L;

	public TipoDadoEquipamentoPK() {
		super();
	}

	public TipoDadoEquipamentoPK(Long idTipoDado, Long idEquipamento) {
		super();
		this.idTipoDado = idTipoDado;
		this.idEquipamento = idEquipamento;
	}

	@Column(name = "fk_tipo_dado")
	private Long idTipoDado;

	@Column(name = "fk_equipamento")
	private Long idEquipamento;

	public Long getIdTipoDado() {
		return idTipoDado;
	}

	public void setIdTipoDado(Long idTipoDado) {
		this.idTipoDado = idTipoDado;
	}

	public Long getIdEquipamento() {
		return idEquipamento;
	}

	public void setIdEquipamento(Long idEquipamento) {
		this.idEquipamento = idEquipamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idEquipamento == null) ? 0 : idEquipamento.hashCode());
		result = prime * result + ((idTipoDado == null) ? 0 : idTipoDado.hashCode());
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
		TipoDadoEquipamentoPK other = (TipoDadoEquipamentoPK) obj;
		if (idEquipamento == null) {
			if (other.idEquipamento != null)
				return false;
		} else if (!idEquipamento.equals(other.idEquipamento))
			return false;
		if (idTipoDado == null) {
			if (other.idTipoDado != null)
				return false;
		} else if (!idTipoDado.equals(other.idTipoDado))
			return false;
		return true;
	}

}
