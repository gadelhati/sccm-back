package br.com.fattoria.sccm.api;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import br.com.fattoria.sccm.controller.MidiaParticularController;
import br.com.fattoria.sccm.persistence.model.MidiaParticular;

public class MidiaParticularModelAssembler extends RepresentationModelAssemblerSupport<MidiaParticular, MidiaParticularModel> {

	
	public MidiaParticularModelAssembler() {		
		super(MidiaParticularController.class, MidiaParticularModel.class);
	}

	@Override
	public MidiaParticularModel toModel(MidiaParticular entity) {
		
		MidiaParticularModel resource = createResource(entity);
		
		return resource;
	}
	
	MidiaParticularModel createResource(MidiaParticular midia){
		
		MidiaParticularModel  model = new MidiaParticularModel(midia);

		return model;
	}

	@Override
	public CollectionModel<MidiaParticularModel> toCollectionModel(Iterable<? extends MidiaParticular> entities) {
		return super.toCollectionModel(entities);
	}
	
}
