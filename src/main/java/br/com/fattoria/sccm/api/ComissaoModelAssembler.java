package br.com.fattoria.sccm.api;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import br.com.fattoria.sccm.controller.ComissaoController;
import br.com.fattoria.sccm.persistence.model.Comissao;

public class ComissaoModelAssembler extends RepresentationModelAssemblerSupport<Comissao, ComissaoModel> {

	public ComissaoModelAssembler() {
		super(ComissaoController.class, ComissaoModel.class);
	}

	@Override
	public ComissaoModel toModel(Comissao entity) {
		
		ComissaoModel resource = createResource(entity);
		
		return resource;
	}
	
	ComissaoModel createResource(Comissao destino){
		
		ComissaoModel model = new ComissaoModel(destino);

		return model;
	}

	@Override
	public CollectionModel<ComissaoModel> toCollectionModel(Iterable<? extends Comissao> entities) {
		return super.toCollectionModel(entities);
	}
	
}
