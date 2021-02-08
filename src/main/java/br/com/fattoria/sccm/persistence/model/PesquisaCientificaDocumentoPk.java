package br.com.fattoria.sccm.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PesquisaCientificaDocumentoPk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "fk_pesquisa_cientifica")
	private Long idPesquisaCientifica;

	@Column(name = "fk_documento")
	private Long idDocumento;

	public PesquisaCientificaDocumentoPk() {
		super();
	}

	public PesquisaCientificaDocumentoPk(Long idPesquisaCientifica, Long idDocumento) {
		super();
		this.idPesquisaCientifica = idPesquisaCientifica;
		this.idDocumento = idDocumento;
	}

	public Long getIdPesquisaCientifica() {
		return idPesquisaCientifica;
	}

	public void setIdPesquisaCientifica(Long idPesquisaCientifica) {
		this.idPesquisaCientifica = idPesquisaCientifica;
	}

	public Long getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idDocumento == null) ? 0 : idDocumento.hashCode());
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
		PesquisaCientificaDocumentoPk other = (PesquisaCientificaDocumentoPk) obj;
		if (idDocumento == null) {
			if (other.idDocumento != null)
				return false;
		} else if (!idDocumento.equals(other.idDocumento))
			return false;
		if (idPesquisaCientifica == null) {
			if (other.idPesquisaCientifica != null)
				return false;
		} else if (!idPesquisaCientifica.equals(other.idPesquisaCientifica))
			return false;
		return true;
	}

}
