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
@Table(name = "pesquisa_cientifica_dados")
public class PesquisaCientificaDados implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PesquisaCientificaDadosPk pk;
	
	@ManyToOne
	@JoinColumn(name = "fk_pesquisa_cientifica", insertable = false, updatable = false)
	private PesquisaCientifica pesquisaCientifica;
	
	@ManyToOne
	@JoinColumn(name = "fk_tipo_dados", insertable = false, updatable = false)
	private TipoDado tipoDado;

	public PesquisaCientificaDados(PesquisaCientificaDadosPk pk) {
		super();
		this.pk = pk;
	}
	
}
