package br.com.fattoria.sccm.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.hateoas.RepresentationModel;

import br.com.fattoria.sccm.controller.TipoAnexoController;
import br.com.fattoria.sccm.persistence.model.TipoAnexo;
import lombok.Getter;

@Getter
public class TipoAnexoModel extends RepresentationModel<TipoAnexoModel> {

	private Long id;
	
	private String descricao;
	
	public TipoAnexoModel(TipoAnexo tipoAnexo) {		
		this.id = tipoAnexo.getId();
		this.descricao = tipoAnexo.getDescricao();
		
		add(linkTo(TipoAnexoController.class).withRel("descricao"));
    	    	
	}
	
}
