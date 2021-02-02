package br.com.fattoria.sccm.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.RepresentationModel;

import br.com.fattoria.sccm.controller.TipoDadoController;
import br.com.fattoria.sccm.controller.UnidadeMedidaController;
import br.com.fattoria.sccm.persistence.model.TipoDado;
import lombok.Getter;

@Getter
public class TipoDadoModel extends RepresentationModel<TipoDadoModel> {
	
	private Long id;
	
	private String descricao;

	private boolean ativo;

	private UnidadeMedidaModel unidadeMedida;
    
    public TipoDadoModel(TipoDado tipoDado) {
    	this.id = tipoDado.getId();
    	this.descricao = tipoDado.getDescricao();
    	this.ativo = tipoDado.isAtivo();
    	this.unidadeMedida = tipoDado.getUnidadeMedida() != null ? new UnidadeMedidaModel(tipoDado.getUnidadeMedida()) : null;
    	add(linkTo(TipoDadoController.class).withRel("tipos_dados"));
    	if(tipoDado.getUnidadeMedida() != null && tipoDado.getUnidadeMedida().getId() != null) {
    		add(linkTo(methodOn(UnidadeMedidaController.class).getById(tipoDado.getUnidadeMedida().getId())).withRel("unidades_medida"));
    	}
        add(linkTo(methodOn(TipoDadoController.class).getById(tipoDado.getId())).withSelfRel());
	}

}
