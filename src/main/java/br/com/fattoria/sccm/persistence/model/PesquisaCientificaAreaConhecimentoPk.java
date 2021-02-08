package br.com.fattoria.sccm.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PesquisaCientificaAreaConhecimentoPk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "fk_pesquisa_cientifica")
	private Long idPesquisaCientifica;

	@Column(name = "fk_area_conhecimento")
	private Long idAreaConhecimento;

	public PesquisaCientificaAreaConhecimentoPk() {
		super();
	}

	public PesquisaCientificaAreaConhecimentoPk(Long idPesquisaCientifica, Long idAreaConhecimento) {
		super();
		this.idPesquisaCientifica = idPesquisaCientifica;
		this.idAreaConhecimento = idAreaConhecimento;
	}

	public Long getIdPesquisaCientifica() {
		return idPesquisaCientifica;
	}

	public void setIdPesquisaCientifica(Long idPesquisaCientifica) {
		this.idPesquisaCientifica = idPesquisaCientifica;
	}

	public Long getIdAreaConhecimento() {
		return idAreaConhecimento;
	}

	public void setIdAreaConhecimento(Long idAreaConhecimento) {
		this.idAreaConhecimento = idAreaConhecimento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAreaConhecimento == null) ? 0 : idAreaConhecimento.hashCode());
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
		PesquisaCientificaAreaConhecimentoPk other = (PesquisaCientificaAreaConhecimentoPk) obj;
		if (idAreaConhecimento == null) {
			if (other.idAreaConhecimento != null)
				return false;
		} else if (!idAreaConhecimento.equals(other.idAreaConhecimento))
			return false;
		if (idPesquisaCientifica == null) {
			if (other.idPesquisaCientifica != null)
				return false;
		} else if (!idPesquisaCientifica.equals(other.idPesquisaCientifica))
			return false;
		return true;
	}

}
