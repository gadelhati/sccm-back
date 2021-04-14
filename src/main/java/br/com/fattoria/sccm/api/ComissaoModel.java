package br.com.fattoria.sccm.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Calendar;

import org.springframework.hateoas.RepresentationModel;

import br.com.fattoria.sccm.controller.ComissaoController;
import br.com.fattoria.sccm.persistence.model.Comissao;
import lombok.Getter;

@Getter
public class ComissaoModel extends RepresentationModel<ComissaoModel> {

	private Long id;	
	
	private String nomeComissao;
	
	private Calendar dataInicio;
	
	private Calendar dataFim;
	
	private String resumo;
	
	private String palavrasChaves;
	
	private TipoComissaoModel tipoComissao;
	
	public ComissaoModel(Comissao comissao) {		
		this.id           = comissao.getId();
		this.nomeComissao = comissao.getNomeComissao();
		this.dataInicio   = comissao.getDataInicio();
		this.dataFim      = comissao.getDataFim();
		this.resumo       = comissao.getResumo();
		this.palavrasChaves = comissao.getPalavrasChaves();
		this.tipoComissao = comissao.getTipoComissao() != null ? new TipoComissaoModel(comissao.getTipoComissao()) : null;
		
		add(linkTo(methodOn(ComissaoController.class).getAll()).withRel("comissoes"));
		add(linkTo(methodOn(ComissaoController.class).getById(comissao.getId())).withSelfRel());
    	    	
	}
	

	
}
