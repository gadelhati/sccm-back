package br.com.fattoria.sccm.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.hateoas.RepresentationModel;

import br.com.fattoria.sccm.controller.TipoEnderecoController;
import br.com.fattoria.sccm.persistence.model.TipoEndereco;
import lombok.Getter;

@Getter
public class TipoEnderecoModel extends RepresentationModel<TipoEnderecoModel> {

	private Long id;
	
	private String descricao;

    public TipoEnderecoModel(TipoEndereco tipoEndereco) {
    	this.id = tipoEndereco.getId();
    	this.descricao = tipoEndereco.getDescricao();
    	add(linkTo(TipoEnderecoController.class).withRel("tipoEndereco"));        
	}
	
}
