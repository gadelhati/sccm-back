package br.com.fattoria.sccm.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.hateoas.RepresentationModel;

import br.com.fattoria.sccm.controller.DestinoController;
import br.com.fattoria.sccm.persistence.model.Destino;
import lombok.Getter;

@Getter
public class DestinoModel extends RepresentationModel<DestinoModel>{

	private Long id;
	
	private String destino;
	
	public DestinoModel(Destino destino) {		
		this.id = destino.getId();
		this.destino= destino.getDestino();
		
		add(linkTo(DestinoController.class).withRel("destino"));
    	    	
	}
	
}
