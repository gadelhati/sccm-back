package br.com.fattoria.sccm.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PesquisaCientificaDadosPk implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "fk_pesquisa_cientifica")
	private Long idPesquisaCientifica;

	@Column(name = "fk_tipo_dados")
	private Long idTipoDado;

	public PesquisaCientificaDadosPk() {}

	public PesquisaCientificaDadosPk(Long idPesquisaCientifica, Long idTipoDado) {
		this.idPesquisaCientifica = idPesquisaCientifica;
		this.idTipoDado = idTipoDado;
	}

	public Long getIdPesquisaCientifica() {
		return idPesquisaCientifica;
	}

	public void setIdPesquisaCientifica(Long idPesquisaCientifica) {
		this.idPesquisaCientifica = idPesquisaCientifica;
	}

	public Long getIdTipoDado() {
		return idTipoDado;
	}

	public void setIdTipoDado(Long idTipoDado) {
		this.idTipoDado = idTipoDado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPesquisaCientifica == null) ? 0 : idPesquisaCientifica.hashCode());
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
		PesquisaCientificaDadosPk other = (PesquisaCientificaDadosPk) obj;
		if (idPesquisaCientifica == null) {
			if (other.idPesquisaCientifica != null)
				return false;
		} else if (!idPesquisaCientifica.equals(other.idPesquisaCientifica))
			return false;
		if (idTipoDado == null) {
			if (other.idTipoDado != null)
				return false;
		} else if (!idTipoDado.equals(other.idTipoDado))
			return false;
		return true;
	}

}
