package br.com.fattoria.sccm.api;

import org.springframework.hateoas.RepresentationModel;

import br.com.fattoria.sccm.persistence.model.TipoMidia;
import lombok.Getter;

@Getter
public class TipoMidiaModel extends RepresentationModel<TipoMidiaModel> {
	
	private Long id;
	
	private String descricao;
	
    public TipoMidiaModel(TipoMidia midia) {
    	this.id = midia.getId();
    	this.descricao = midia.getDescricao();
    	//add(linkTo(TipoMidiaRepository.class).withRel("tipos_midia"));
        //add(linkTo(methodOn(TipoMidiaRepository.class).findById(midia.getId())).withSelfRel());
	}

}
