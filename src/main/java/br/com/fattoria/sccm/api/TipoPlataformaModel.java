package br.com.fattoria.sccm.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.RepresentationModel;

import br.com.fattoria.sccm.controller.PlataformaController;
import br.com.fattoria.sccm.controller.TipoPlataformaController;
import br.com.fattoria.sccm.persistence.model.TipoPlataforma;
import lombok.Getter;

@Getter
public class TipoPlataformaModel extends RepresentationModel<TipoPlataformaModel> {
	
	private Long id;
	
	private String descricao;

	private boolean ativo;

    
    public TipoPlataformaModel(TipoPlataforma tipoPlataforma) {
    	this.id = tipoPlataforma.getId();
    	this.descricao = tipoPlataforma.getDescricao();
    	this.ativo = tipoPlataforma.isAtivo();
    	add(linkTo(TipoPlataformaController.class).withRel("tipos_plataforma"));
        add(linkTo(methodOn(PlataformaController.class).getById(tipoPlataforma.getId())).withSelfRel());
	}

}
