package br.com.fattoria.sccm.api;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import br.com.fattoria.sccm.controller.DestinoController;
import br.com.fattoria.sccm.persistence.model.Destino;
import br.com.fattoria.sccm.persistence.model.Equipamento;

public class DestinoModelAssembler extends RepresentationModelAssemblerSupport<Destino, DestinoModel> {

	public DestinoModelAssembler() {
		super(DestinoController.class, DestinoModel.class);
	}

	@Override
	public DestinoModel toModel(Destino entity) {
		
		DestinoModel resource = createResource(entity);
		
		return resource;
	}
	
	DestinoModel createResource(Destino destino){
		
		DestinoModel model = new DestinoModel(destino);

		return model;
	}

	@Override
	public CollectionModel<DestinoModel> toCollectionModel(Iterable<? extends Destino> entities) {
		return super.toCollectionModel(entities);
	}
	
}
