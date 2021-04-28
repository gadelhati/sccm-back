package br.com.fattoria.sccm.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.RepresentationModel;

import br.com.fattoria.sccm.controller.AssinaturaPCController;
import br.com.fattoria.sccm.controller.DestinoController;
import br.com.fattoria.sccm.persistence.model.AssinaturaPC;
import br.com.fattoria.sccm.persistence.model.Destino;
import lombok.Getter;

@Getter
public class AssinaturaPCModel extends RepresentationModel<AssinaturaPCModel> {
	
	private Long id;	
	
	private String nome;
	
	private Destino destino;
	
	private Boolean ativo;
	
	public AssinaturaPCModel(AssinaturaPC assinaturaPC) {
		
		this.id = assinaturaPC.getId();
		
		this.nome = assinaturaPC.getNome();
		
		this.destino = assinaturaPC.getDestino() != null ? assinaturaPC.getDestino() : null;
		
		this.ativo = assinaturaPC.getAtivo();
		
		add(linkTo(methodOn(AssinaturaPCController.class)).withRel("assinaturas"));
		
		if (assinaturaPC != null && assinaturaPC.getDestino() != null) {

			add(linkTo(methodOn(DestinoController.class).getById(assinaturaPC.getDestino().getId())).withRel("destino"));	
		}
	}

}
