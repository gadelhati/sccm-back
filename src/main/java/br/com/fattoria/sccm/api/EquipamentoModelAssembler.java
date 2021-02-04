package br.com.fattoria.sccm.api;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import br.com.fattoria.sccm.controller.EquipamentoController;
import br.com.fattoria.sccm.persistence.model.Equipamento;

public class EquipamentoModelAssembler extends RepresentationModelAssemblerSupport<Equipamento, EquipamentoModel> {
	
	public EquipamentoModelAssembler() {
		super(EquipamentoController.class, EquipamentoModel.class);
	}

	@Override
	public EquipamentoModel toModel(Equipamento entity) {
		
		EquipamentoModel resource = createResource(entity);
		
		return resource;
	}
	
	EquipamentoModel createResource(Equipamento equipamento){
		
		EquipamentoModel model = new EquipamentoModel(equipamento);

		return model;
	}

	@Override
	public CollectionModel<EquipamentoModel> toCollectionModel(Iterable<? extends Equipamento> entities) {
		return super.toCollectionModel(entities);
	}
	
	
}
