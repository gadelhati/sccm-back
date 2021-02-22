package br.com.fattoria.sccm.api;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import br.com.fattoria.sccm.controller.MidiaParticularController;
import br.com.fattoria.sccm.persistence.model.MidiaParticularTipoMidia;

public class MidiaParticularTipoMidiaModelAssembler extends RepresentationModelAssemblerSupport<MidiaParticularTipoMidia, MidiaParticularTipoMidiaModel> {

	
	public MidiaParticularTipoMidiaModelAssembler() {		
		super(MidiaParticularController.class, MidiaParticularTipoMidiaModel.class);
	}

	@Override
	public MidiaParticularTipoMidiaModel toModel(MidiaParticularTipoMidia entity) {
		
		MidiaParticularTipoMidiaModel resource = createResource(entity);
		
		return resource;
	}
	
	MidiaParticularTipoMidiaModel createResource(MidiaParticularTipoMidia midiaDiversaTipoMidia){
		
		MidiaParticularTipoMidiaModel  model = new MidiaParticularTipoMidiaModel(midiaDiversaTipoMidia);

		return model;
	}

	@Override
	public CollectionModel<MidiaParticularTipoMidiaModel> toCollectionModel(Iterable<? extends MidiaParticularTipoMidia> entities) {
		return super.toCollectionModel(entities);
	}
	
}
