package br.com.fattoria.sccm.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PesquisaCientificaEquipamentoPk implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "fk_pesquisa_cientifica")
	private Long idPesquisaCientifica;

	@Column(name = "fk_documento")
	private Long idEquipamento;

	public PesquisaCientificaEquipamentoPk() {
		super();
	}

	public PesquisaCientificaEquipamentoPk(Long idPesquisaCientifica, Long idEquipamento) {
		super();
		this.idPesquisaCientifica = idPesquisaCientifica;
		this.idEquipamento = idEquipamento;
	}

	public Long getIdPesquisaCientifica() {
		return idPesquisaCientifica;
	}

	public void setIdPesquisaCientifica(Long idPesquisaCientifica) {
		this.idPesquisaCientifica = idPesquisaCientifica;
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
		result = prime * result + ((idPesquisaCientifica == null) ? 0 : idPesquisaCientifica.hashCode());
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
		PesquisaCientificaEquipamentoPk other = (PesquisaCientificaEquipamentoPk) obj;
		if (idEquipamento == null) {
			if (other.idEquipamento != null)
				return false;
		} else if (!idEquipamento.equals(other.idEquipamento))
			return false;
		if (idPesquisaCientifica == null) {
			if (other.idPesquisaCientifica != null)
				return false;
		} else if (!idPesquisaCientifica.equals(other.idPesquisaCientifica))
			return false;
		return true;
	}


}
