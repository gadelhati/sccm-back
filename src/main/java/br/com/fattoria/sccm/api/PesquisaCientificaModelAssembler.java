package br.com.fattoria.sccm.api;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import br.com.fattoria.sccm.controller.PesquisaCientificaController;
import br.com.fattoria.sccm.persistence.model.PesquisaCientifica;

public class PesquisaCientificaModelAssembler extends RepresentationModelAssemblerSupport<PesquisaCientifica, PesquisaCientificaModel> {

	public PesquisaCientificaModelAssembler() {
		super(PesquisaCientificaController.class, PesquisaCientificaModel.class);
	}

	@Override
	public PesquisaCientificaModel toModel(PesquisaCientifica entity) {
		
		PesquisaCientificaModel resource = createResource(entity);
		
		return resource;
	}
	
	PesquisaCientificaModel createResource(PesquisaCientifica entity){
		
		PesquisaCientificaModel model = new PesquisaCientificaModel(entity);

		return model;
	}

	@Override
	public CollectionModel<PesquisaCientificaModel> toCollectionModel(Iterable<? extends PesquisaCientifica> entities) {
		return super.toCollectionModel(entities);
	}
	
}
