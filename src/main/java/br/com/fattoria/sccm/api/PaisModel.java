package br.com.fattoria.sccm.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.RepresentationModel;

import br.com.fattoria.sccm.controller.PaisController;
import br.com.fattoria.sccm.persistence.model.Pais;
import lombok.Getter;

@Getter
public class PaisModel extends RepresentationModel<PaisModel> {
	
	private Long id;
	
	private String nome;
	
	private String nomeIngles;

    public PaisModel(Pais pais) {
    	this.id = pais.getId();
    	this.nome = pais.getNome();
    	this.nomeIngles = pais.getNomeIngles();
    	add(linkTo(methodOn(PaisController.class).getAll()).withRel("paises"));
        add(linkTo(methodOn(PaisController.class).getById(pais.getId())).withSelfRel());
	}

}
