package br.com.fattoria.sccm.api;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import br.com.fattoria.sccm.controller.ControleInternoController;
import br.com.fattoria.sccm.persistence.model.ControleInterno;


public class ControleInternoModelAssembler extends RepresentationModelAssemblerSupport<ControleInterno, ControleInternoModel> {
	
	public ControleInternoModelAssembler() {
		super(ControleInternoController.class, ControleInternoModel.class);
	}

	@Override
	public ControleInternoModel toModel(ControleInterno entity) {
		
		ControleInternoModel resource = createResource(entity);
		
		return resource;
	}
	
	ControleInternoModel createResource(ControleInterno resource){
		
		ControleInternoModel model = new ControleInternoModel(resource);

		return model;
	}

	@Override
	public CollectionModel<ControleInternoModel> toCollectionModel(Iterable<? extends ControleInterno> entities) {
		return super.toCollectionModel(entities);
	}

}
