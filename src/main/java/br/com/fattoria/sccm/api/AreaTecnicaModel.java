package br.com.fattoria.sccm.api;

import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.fattoria.sccm.controller.AreaTecnicaController;
import br.com.fattoria.sccm.controller.EquipamentoController;
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
