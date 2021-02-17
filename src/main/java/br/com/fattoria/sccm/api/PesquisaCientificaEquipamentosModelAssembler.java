package br.com.fattoria.sccm.api;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import br.com.fattoria.sccm.controller.PesquisaCientificaDadosController;
import br.com.fattoria.sccm.persistence.model.PesquisaCientificaEquipamento;

public class PesquisaCientificaEquipamentosModelAssembler extends RepresentationModelAssemblerSupport<PesquisaCientificaEquipamento, PesquisaCientificaEquipamentosModel> {

	public PesquisaCientificaEquipamentosModelAssembler() {
		super(PesquisaCientificaDadosController.class, PesquisaCientificaEquipamentosModel.class);
	}

	@Override
	public PesquisaCientificaEquipamentosModel toModel(PesquisaCientificaEquipamento entity) {
		
		PesquisaCientificaEquipamentosModel resource = createResource(entity);
		
		return resource;
	}
	
	PesquisaCientificaEquipamentosModel createResource(PesquisaCientificaEquipamento entity){
		
		PesquisaCientificaEquipamentosModel model = new PesquisaCientificaEquipamentosModel(entity);

		return model;
	}

	@Override
	public CollectionModel<PesquisaCientificaEquipamentosModel> toCollectionModel(Iterable<? extends PesquisaCientificaEquipamento> entities) {
		return super.toCollectionModel(entities);
	}
	
}
