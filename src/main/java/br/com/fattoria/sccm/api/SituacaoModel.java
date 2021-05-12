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
	
	private boolean paraPesquisaCientifica;
	
	private boolean paraShipSynop;
	
	private boolean paraMidiasDiversas;
	
	private boolean paraMidiasParticulares;
	
	private boolean ativo;
	
	public SituacaoModel(Situacao situacao) {		
		this.id = situacao.getId();
		this.descricao= situacao.getDescricao();
		this.paraPesquisaCientifica = situacao.isParaPesquisaCientifica();
		this.paraShipSynop = situacao.isParaShipSynop();
		this.paraMidiasDiversas = situacao.isParaMidiasDiversas();
		this.paraMidiasParticulares = situacao.isParaMidiasParticulares();
		this.ativo = situacao.isAtivo();
		
		add(linkTo(DestinoController.class).withRel("situacoes"));
    	    	
	}
	
}
