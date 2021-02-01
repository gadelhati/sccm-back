package br.com.fattoria.sccm.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.hateoas.RepresentationModel;

import br.com.fattoria.sccm.controller.AreaConhecimentoController;
import br.com.fattoria.sccm.persistence.model.AreaConhecimento;
import lombok.Getter;

@Getter
public class AreaConhecimentoModel extends RepresentationModel<AreaConhecimentoModel> {

	private Long id;
	
	private String nome;

	private String descricao;

	private boolean ativo;
    
    public AreaConhecimentoModel(AreaConhecimento areaConhecimento) {
    	this.id = areaConhecimento.getId();
    	this.nome = areaConhecimento.getNome();
    	this.descricao = areaConhecimento.getDescricao();
    	this.ativo = areaConhecimento.isAtivo();
    	
    	add(linkTo(AreaConhecimentoController.class).withRel("areaConhecimento"));
    	
	}
	
}
