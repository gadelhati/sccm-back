package br.com.fattoria.sccm.api;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import br.com.fattoria.sccm.controller.PalavraChaveController;
import br.com.fattoria.sccm.persistence.model.PalavraChave;

public class PalavraChaveModelAssembler extends RepresentationModelAssemblerSupport<PalavraChave, PalavraChaveModel> {

	public PalavraChaveModelAssembler() {
		super(PalavraChaveController.class, PalavraChaveModel.class);
	}

	@Override
	public PalavraChaveModel toModel(PalavraChave entity) {
		
		PalavraChaveModel resource = createResource(entity);
		
		return resource;
	}
	
	PalavraChaveModel createResource(PalavraChave entity){
		
		PalavraChaveModel model = new PalavraChaveModel(entity);

		return model;
	}

	@Override
	public CollectionModel<PalavraChaveModel> toCollectionModel(Iterable<? extends PalavraChave> entities) {
		return super.toCollectionModel(entities);
	}
	
}
