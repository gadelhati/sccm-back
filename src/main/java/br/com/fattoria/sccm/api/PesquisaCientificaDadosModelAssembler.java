package br.com.fattoria.sccm.api;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import br.com.fattoria.sccm.controller.PesquisaCientificaDadosController;
import br.com.fattoria.sccm.persistence.model.PesquisaCientificaDados;

public class PesquisaCientificaDadosModelAssembler extends RepresentationModelAssemblerSupport<PesquisaCientificaDados, PesquisaCientificaDadosModel> {

	public PesquisaCientificaDadosModelAssembler() {
		super(PesquisaCientificaDadosController.class, PesquisaCientificaDadosModel.class);
	}

	@Override
	public PesquisaCientificaDadosModel toModel(PesquisaCientificaDados entity) {
		
		PesquisaCientificaDadosModel resource = createResource(entity);
		
		return resource;
	}
	
	PesquisaCientificaDadosModel createResource(PesquisaCientificaDados entity){
		
		PesquisaCientificaDadosModel model = new PesquisaCientificaDadosModel(entity);

		return model;
	}

	@Override
	public CollectionModel<PesquisaCientificaDadosModel> toCollectionModel(Iterable<? extends PesquisaCientificaDados> entities) {
		return super.toCollectionModel(entities);
	}
	
}
