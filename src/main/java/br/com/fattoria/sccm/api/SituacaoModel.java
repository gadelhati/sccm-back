package br.com.fattoria.sccm.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.hateoas.RepresentationModel;

import br.com.fattoria.sccm.controller.DestinoController;
import br.com.fattoria.sccm.persistence.model.Situacao;
import lombok.Getter;

@Getter
public class SituacaoModel extends RepresentationModel<SituacaoModel>{

	private Long id;
	
	private String descricao;
	
	private boolean paraPequisaCientifica;
	
	private boolean paraShipSynop;
	
	public SituacaoModel(Situacao situacao) {		
		this.id = situacao.getId();
		this.descricao= situacao.getDescricao();
		this.paraPequisaCientifica = situacao.isParaPequisaCientifica();
		this.paraShipSynop = situacao.isParaShipSynop();
		
		add(linkTo(DestinoController.class).withRel("situacoes"));
    	    	
	}
	
}
