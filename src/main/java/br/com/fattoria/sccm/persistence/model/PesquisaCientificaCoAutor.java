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
@Table(name = "pesquisa_cientifica_co_autor")
public class PesquisaCientificaCoAutor implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PesquisaCientificaCoAutorPk pk;

	@ManyToOne
	@JoinColumn(name = "fk_pesquisa_cientifica", insertable = false, updatable = false)
	private PesquisaCientifica pesquisaCientifica;

	@ManyToOne
	@JoinColumn(name = "fk_instituicao", insertable = false, updatable = false)
	private Instituicao instituicao;

	public PesquisaCientificaCoAutor(PesquisaCientificaCoAutorPk pk) {
		super();
		this.pk = pk;
	}

}
