package br.com.fattoria.sccm.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PesquisaCientificaCoAutorPk implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "fk_pesquisa_cientifica")
	private Long idPesquisaCientifica;
	
	@Column(name = "fk_instituicao")
	private Long idInstituicao;
	
	public PesquisaCientificaCoAutorPk(Long idPesquisaCientifica, Long idInstituicao) {
		this.idPesquisaCientifica = idPesquisaCientifica;
		this.idInstituicao = idInstituicao;
	}

	public PesquisaCientificaCoAutorPk() {}

	public Long getIdPesquisaCientifica() {
		return idPesquisaCientifica;
	}

	public void setIdPesquisaCientifica(Long idPesquisaCientifica) {
		this.idPesquisaCientifica = idPesquisaCientifica;
	}

	public Long getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Long idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idInstituicao == null) ? 0 : idInstituicao.hashCode());
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
		PesquisaCientificaCoAutorPk other = (PesquisaCientificaCoAutorPk) obj;
		if (idInstituicao == null) {
			if (other.idInstituicao != null)
				return false;
		} else if (!idInstituicao.equals(other.idInstituicao))
			return false;
		if (idPesquisaCientifica == null) {
			if (other.idPesquisaCientifica != null)
				return false;
		} else if (!idPesquisaCientifica.equals(other.idPesquisaCientifica))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PesquisaCientificaCoAutorPk [idPesquisaCientifica=" + idPesquisaCientifica + ", idInstituicao="
				+ idInstituicao + "]";
	}
	
}
