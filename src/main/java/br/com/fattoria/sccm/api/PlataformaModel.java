package br.com.fattoria.sccm.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.RepresentationModel;

import br.com.fattoria.sccm.controller.PaisController;
import br.com.fattoria.sccm.controller.PlataformaController;
import br.com.fattoria.sccm.controller.TipoPlataformaController;
import br.com.fattoria.sccm.persistence.model.Plataforma;
import lombok.Getter;

@Getter
public class PlataformaModel extends RepresentationModel<PlataformaModel> {
	
	private Long id;
	
	private String nome;

	private String indicativoVisual;
	
	private boolean ativo;
	
	private TipoPlataformaModel tipoPlataforma;
	
	private PaisModel bandeira;
    
    public PlataformaModel(Plataforma plataforma) {
    	this.id = plataforma.getId();
    	this.nome = plataforma.getNome();
    	this.indicativoVisual = plataforma.getIndicativoVisual();
    	this.ativo = plataforma.isAtivo();
    	this.tipoPlataforma = plataforma.getTipoPlataforma() != null ? new TipoPlataformaModel(plataforma.getTipoPlataforma()) : null;
    	this.bandeira = plataforma.getBandeira() != null ? new PaisModel(plataforma.getBandeira()) : null;
    	
    	add(linkTo(PlataformaController.class).withRel("plataformas"));
    	if(plataforma.getTipoPlataforma() != null && plataforma.getTipoPlataforma().getId() != null) {
    		add(linkTo(methodOn(TipoPlataformaController.class).getById(plataforma.getTipoPlataforma().getId())).withRel("tipos_plataforma"));
    	}
    	if(plataforma.getBandeira() != null && plataforma.getBandeira().getId() != null) {
    		add(linkTo(methodOn(PaisController.class).getById(plataforma.getBandeira().getId())).withRel("paises"));
    	}
        add(linkTo(methodOn(PlataformaController.class).getById(plataforma.getId())).withSelfRel());
	}
	
}
