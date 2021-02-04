package br.com.fattoria.sccm.api;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import br.com.fattoria.sccm.controller.MetodoAmostragemController;
import br.com.fattoria.sccm.persistence.model.MetodoAmostragem;

public class MetodoAmostragemModelAssembler extends RepresentationModelAssemblerSupport<MetodoAmostragem, MetodoAmostragemModel> {

	
	public MetodoAmostragemModelAssembler() {		
		super(MetodoAmostragemController.class, MetodoAmostragemModel.class);
	}

	@Override
	public MetodoAmostragemModel toModel(MetodoAmostragem entity) {
		
		MetodoAmostragemModel resource = createResource(entity);
		
		return resource;
	}
	
	MetodoAmostragemModel createResource(MetodoAmostragem metodoAmostragem){
		
		MetodoAmostragemModel  model = new MetodoAmostragemModel(metodoAmostragem);

		return model;
	}

	@Override
	public CollectionModel<MetodoAmostragemModel> toCollectionModel(Iterable<? extends MetodoAmostragem> entities) {
		return super.toCollectionModel(entities);
	}
	
}
