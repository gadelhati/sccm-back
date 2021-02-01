package br.com.fattoria.sccm.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.hateoas.RepresentationModel;

import br.com.fattoria.sccm.controller.UnidadeMedidaController;
import br.com.fattoria.sccm.persistence.model.UnidadeMedida;

public class UnidadeMedidaModel extends RepresentationModel<UnidadeMedidaModel> {

	private Long id;
	
	private String descricao;
	
	public UnidadeMedidaModel(UnidadeMedida unidadeMedida) {
		this.id = unidadeMedida.getId();
		this.descricao = unidadeMedida.getDescricao();
		add(linkTo(UnidadeMedidaController.class).withRel("unidadeMedida"));
		
	}
	
}
