package br.com.fattoria.sccm.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.hateoas.RepresentationModel;

import br.com.fattoria.sccm.controller.PlataformaController;
import br.com.fattoria.sccm.persistence.model.Pais;
import br.com.fattoria.sccm.persistence.model.Plataforma;
import br.com.fattoria.sccm.persistence.model.TipoPlataforma;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlataformaModel extends RepresentationModel<PlataformaModel> {
	
	private Long id;
	
	private String nome;

	private String identificadorVisual;
	
	private boolean ativo;

    
    public PlataformaModel(Plataforma plataforma) {
    	this.id = plataforma.getId();
    	this.nome = plataforma.getNome();
    	this.identificadorVisual = plataforma.getIdentificadorVisual();
    	this.ativo = plataforma.isAtivo();
    	add(linkTo(PlataformaController.class).withRel("plataformas"));
//        add(linkTo(methodOn(GymMembershipController.class).all(id)).withRel("memberships"));
        add(linkTo(methodOn(PlataformaController.class).getById(plataforma.getId())).withSelfRel());
	}
	
}
