package br.com.fattoria.sccm.api;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import br.com.fattoria.sccm.controller.UnidadeMedidaController;
import br.com.fattoria.sccm.persistence.model.UnidadeMedida;

public class UnidadeMedidaModelAssembler extends RepresentationModelAssemblerSupport<UnidadeMedida, UnidadeMedidaModel> {
	
	public UnidadeMedidaModelAssembler() {
		super(UnidadeMedidaController.class, UnidadeMedidaModel.class);
	}

	@Override
	public UnidadeMedidaModel toModel(UnidadeMedida entity) {
		
		UnidadeMedidaModel resource = createResource(entity);
		
		return resource;
	}
	
	UnidadeMedidaModel createResource(UnidadeMedida unidadeMedida){
		
		UnidadeMedidaModel model = new UnidadeMedidaModel(unidadeMedida);

		return model;
	}
	
	@Override
	public CollectionModel<UnidadeMedidaModel> toCollectionModel(Iterable<? extends UnidadeMedida> entities) {
		return super.toCollectionModel(entities);
	}

}
