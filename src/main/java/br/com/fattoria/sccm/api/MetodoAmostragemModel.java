package br.com.fattoria.sccm.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.hateoas.RepresentationModel;

import br.com.fattoria.sccm.controller.MetodoAmostragemController;
import br.com.fattoria.sccm.persistence.model.MetodoAmostragem;
import lombok.Getter;

@Getter
public class MetodoAmostragemModel extends RepresentationModel<MetodoAmostragemModel> {
		
	private Long id;
	
	private String descricao;

	public MetodoAmostragemModel(MetodoAmostragem metodoAmostragem) {
		this.id = metodoAmostragem.getId();
		this.descricao = metodoAmostragem.getDescricao();
		add(linkTo(MetodoAmostragemController.class).withRel("metodoAmostragem"));        
	}
	
}
