package br.com.fattoria.sccm.api;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import br.com.fattoria.sccm.controller.TipoAnexo;
import br.com.fattoria.sccm.controller.TipoAnexoController;

public class TipoAnexoModelAssembler  extends RepresentationModelAssemblerSupport<TipoAnexo, TipoAnexoModel> {

	public TipoAnexoModelAssembler() {
		super(TipoAnexoController.class, TipoAnexoModel.class);
	}

	@Override
	public TipoAnexoModel toModel(TipoAnexo entity) {
		
		TipoAnexoModel resource = createResource(entity);
		
		return resource;
	}
	
	TipoAnexoModel createResource(TipoAnexo destino){
		
		TipoAnexoModel model = new TipoAnexoModel(destino);

		return model;
	}

	@Override
	public CollectionModel<TipoAnexoModel> toCollectionModel(Iterable<? extends TipoAnexo> entities) {
		return super.toCollectionModel(entities);
	}
	
	
}
