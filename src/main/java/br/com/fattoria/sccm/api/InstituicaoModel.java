package br.com.fattoria.sccm.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.RepresentationModel;

import br.com.fattoria.sccm.controller.InstituicaoController;
import br.com.fattoria.sccm.controller.PaisController;
import br.com.fattoria.sccm.persistence.model.Instituicao;
import br.com.fattoria.sccm.persistence.model.TipoInstituicao;
import lombok.Getter;

@Getter
public class InstituicaoModel extends RepresentationModel<InstituicaoModel> {

	private Long id;

	private String nome;
	
	private String sigla;
	
	private String email;
	
	private String site;
	
	private TipoInstituicao tipoInstituicao;
	
	private Long numeroInscricao;
	
	private Long numeroControle;
	
	private PaisModel pais;
	
	private EnderecoModel endereco;
	
	private boolean ativo;
    
    public InstituicaoModel(Instituicao instituicao) {
    	this.id = instituicao.getId();
    	this.nome = instituicao.getNome();
    	this.sigla = instituicao.getSigla();
    	this.tipoInstituicao = instituicao.getTipoInstituicao();
    	this.numeroInscricao = instituicao.getNumeroInscricao();
    	this.numeroControle = instituicao.getNumeroControle();
    	this.email = instituicao.getEmail();
    	this.site = instituicao.getSite();
    	this.pais = instituicao.getPais() != null ? new PaisModel(instituicao.getPais()) : null;
    	this.endereco = instituicao.getEndereco() != null ? new EnderecoModel(instituicao.getEndereco()) : null;
    	this.ativo = instituicao.isAtivo();
    	
    	add(linkTo(InstituicaoController.class).withRel("instituicoes"));
    	if(instituicao.getPais() != null && instituicao.getPais().getId() != null) {
    		add(linkTo(methodOn(PaisController.class).getById(instituicao.getPais().getId())).withRel("paises"));
    	}
        add(linkTo(methodOn(InstituicaoController.class).getById(instituicao.getId())).withSelfRel());
	}
}
