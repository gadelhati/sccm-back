package br.com.fattoria.sccm.api;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import br.com.fattoria.sccm.controller.AssinaturaPCController;
import br.com.fattoria.sccm.persistence.model.AssinaturaPC;

public class AssinaturaPCModelAssembler extends RepresentationModelAssemblerSupport<AssinaturaPC, AssinaturaPCModel> {

	public AssinaturaPCModelAssembler() {
		super(AssinaturaPCController.class, AssinaturaPCModel.class);
	}
	
	public AssinaturaPCModel toModel(AssinaturaPC entity) {
		
		AssinaturaPCModel resource = createResource(entity);
		
		return resource;
	}
	
	AssinaturaPCModel createResource(AssinaturaPC resource){
		
		AssinaturaPCModel model = new AssinaturaPCModel(resource);

		return model;
	}

	@Override
	public CollectionModel<AssinaturaPCModel> toCollectionModel(Iterable<? extends AssinaturaPC> entities) {
		return super.toCollectionModel(entities);
	}

}
