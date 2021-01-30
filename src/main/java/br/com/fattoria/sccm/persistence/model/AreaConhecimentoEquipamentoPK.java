package br.com.fattoria.sccm.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AreaConhecimentoEquipamentoPK implements Serializable {

	private static final long serialVersionUID = 1L;

	public AreaConhecimentoEquipamentoPK() {
		super();
	}

	public AreaConhecimentoEquipamentoPK(Long idAreaConhecimento, Long idEquipamento) {
		super();
		this.idAreaConhecimento = idAreaConhecimento;
		this.idEquipamento = idEquipamento;
	}

	@Column(name = "fk_area_conhecimento")
	private Long idAreaConhecimento;

	@Column(name = "fk_equipamento")
	private Long idEquipamento;

	public Long getIdAreaConhecimento() {
		return idAreaConhecimento;
	}

	public void setIdAreaConhecimento(Long idAreaConhecimento) {
		this.idAreaConhecimento = idAreaConhecimento;
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
		result = prime * result + ((idAreaConhecimento == null) ? 0 : idAreaConhecimento.hashCode());
		result = prime * result + ((idEquipamento == null) ? 0 : idEquipamento.hashCode());
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
		AreaConhecimentoEquipamentoPK other = (AreaConhecimentoEquipamentoPK) obj;
		if (idAreaConhecimento == null) {
			if (other.idAreaConhecimento != null)
				return false;
		} else if (!idAreaConhecimento.equals(other.idAreaConhecimento))
			return false;
		if (idEquipamento == null) {
			if (other.idEquipamento != null)
				return false;
		} else if (!idEquipamento.equals(other.idEquipamento))
			return false;
		return true;
	}

}
