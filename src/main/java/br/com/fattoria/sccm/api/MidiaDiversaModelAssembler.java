package br.com.fattoria.sccm.api;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import br.com.fattoria.sccm.controller.MidiaDiversaController;
import br.com.fattoria.sccm.persistence.model.MidiaDiversa;

public class MidiaDiversaModelAssembler extends RepresentationModelAssemblerSupport<MidiaDiversa, MidiaDiversaModel> {

	
	public MidiaDiversaModelAssembler() {		
		super(MidiaDiversaController.class, MidiaDiversaModel.class);
	}

	@Override
	public MidiaDiversaModel toModel(MidiaDiversa entity) {
		
		MidiaDiversaModel resource = createResource(entity);
		
		return resource;
	}
	
	MidiaDiversaModel createResource(MidiaDiversa midia){
		
		MidiaDiversaModel  model = new MidiaDiversaModel(midia);

		return model;
	}

	@Override
	public CollectionModel<MidiaDiversaModel> toCollectionModel(Iterable<? extends MidiaDiversa> entities) {
		return super.toCollectionModel(entities);
	}
	
}
