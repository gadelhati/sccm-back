package br.com.fattoria.sccm.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.hateoas.RepresentationModel;

import br.com.fattoria.sccm.controller.AreaTecnicaController;
import br.com.fattoria.sccm.persistence.model.AreaTecnica;
import lombok.Getter;

@Getter
public class AreaTecnicaModel extends RepresentationModel<AreaTecnicaModel> {
	
	private Long id;
	
	private String descricao;
	
	public AreaTecnicaModel(AreaTecnica areaTecnica) {
		this.id = areaTecnica.getId();
		this.descricao = areaTecnica.getDescricao();
		add(linkTo(AreaTecnicaController.class).withRel("areaTecnica"));		
	}
	
	

}
