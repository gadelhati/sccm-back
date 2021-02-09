package br.com.fattoria.sccm.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.RepresentationModel;

import br.com.fattoria.sccm.controller.MidiaDiversaController;
import br.com.fattoria.sccm.persistence.model.MidiaDiversaTipoMidia;
import lombok.Getter;

@Getter
public class MidiaDiversaTipoMidiaModel extends RepresentationModel<MidiaDiversaTipoMidiaModel> {
	
	private MidiaDiversaModel midiaDiversa;
	private TipoMidiaModel tipoMidia;
	private Long quantidade;

    public MidiaDiversaTipoMidiaModel(MidiaDiversaTipoMidia midiaDiversaTipoMidia) {
    	this.midiaDiversa = midiaDiversaTipoMidia.getMidiaDiversa() != null ? new MidiaDiversaModel(midiaDiversaTipoMidia.getMidiaDiversa()) : null;
    	this.tipoMidia = midiaDiversaTipoMidia.getTipoMidia() != null ? new TipoMidiaModel(midiaDiversaTipoMidia.getTipoMidia()) : null;
    	this.quantidade = midiaDiversaTipoMidia.getQuantidade();
    	add(linkTo(MidiaDiversaController.class).withRel("midias_diversas"));
//    	if(tipoMidia != null)
//    		add(linkTo(methodOn(TipoMidiaRepository.class).findById(tipoMidia.getId())).withSelfRel());
    	if(midiaDiversa != null)
    		add(linkTo(methodOn(MidiaDiversaController.class).getById(midiaDiversa.getId())).withSelfRel());
	}

}
