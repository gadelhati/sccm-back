package br.com.fattoria.sccm.persistence.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PesquisaCientificaAreaConhecimentoPk {

	@Column(name = "fk_pesquisa_cientifica")
	private Long idPesquisaCientifica;
	
	@Column(name = "fk_area_conhecimento")
	private Long idAreaConhecimento;
	
}
