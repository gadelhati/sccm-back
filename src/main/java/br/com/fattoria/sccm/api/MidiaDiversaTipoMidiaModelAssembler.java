package br.com.fattoria.sccm.api;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import br.com.fattoria.sccm.controller.MidiaDiversaController;
import br.com.fattoria.sccm.persistence.model.MidiaDiversaTipoMidia;

public class MidiaDiversaTipoMidiaModelAssembler extends RepresentationModelAssemblerSupport<MidiaDiversaTipoMidia, MidiaDiversaTipoMidiaModel> {

	
	public MidiaDiversaTipoMidiaModelAssembler() {		
		super(MidiaDiversaController.class, MidiaDiversaTipoMidiaModel.class);
	}

	@Override
	public MidiaDiversaTipoMidiaModel toModel(MidiaDiversaTipoMidia entity) {
		
		MidiaDiversaTipoMidiaModel resource = createResource(entity);
		
		return resource;
	}
	
	MidiaDiversaTipoMidiaModel createResource(MidiaDiversaTipoMidia midiaDiversaTipoMidia){
		
		MidiaDiversaTipoMidiaModel  model = new MidiaDiversaTipoMidiaModel(midiaDiversaTipoMidia);

		return model;
	}

	@Override
	public CollectionModel<MidiaDiversaTipoMidiaModel> toCollectionModel(Iterable<? extends MidiaDiversaTipoMidia> entities) {
		return super.toCollectionModel(entities);
	}
	
}
