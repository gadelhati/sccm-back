package br.com.fattoria.sccm.api;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import br.com.fattoria.sccm.controller.ShipSynopController;
import br.com.fattoria.sccm.persistence.model.ShipSynop;

public class ShipSynopModelAssembler extends RepresentationModelAssemblerSupport<ShipSynop, ShipSynopModel> {
	
	public ShipSynopModelAssembler() {
		super(ShipSynopController.class, ShipSynopModel.class);
	}

	@Override
	public ShipSynopModel toModel(ShipSynop entity) {
		
		ShipSynopModel resource = createResource(entity);
		
		return resource;
	}
	
	ShipSynopModel createResource(ShipSynop resource){
		
		ShipSynopModel model = new ShipSynopModel(resource);

		return model;
	}

	@Override
	public CollectionModel<ShipSynopModel> toCollectionModel(Iterable<? extends ShipSynop> entities) {
		return super.toCollectionModel(entities);
	}

}
