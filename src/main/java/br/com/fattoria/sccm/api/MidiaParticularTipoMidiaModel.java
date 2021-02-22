package br.com.fattoria.sccm.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.RepresentationModel;

import br.com.fattoria.sccm.controller.MidiaParticularController;
import br.com.fattoria.sccm.persistence.model.MidiaParticularTipoMidia;
import lombok.Getter;

@Getter
public class MidiaParticularTipoMidiaModel extends RepresentationModel<MidiaParticularTipoMidiaModel> {
	
	private MidiaParticularModel midiaParticular;
	private TipoMidiaModel tipoMidia;
	private Long quantidade;

    public MidiaParticularTipoMidiaModel(MidiaParticularTipoMidia midiaDiversaTipoMidia) {
    	this.midiaParticular = midiaDiversaTipoMidia.getMidiaParticular() != null ? new MidiaParticularModel(midiaDiversaTipoMidia.getMidiaParticular()) : null;
    	this.tipoMidia = midiaDiversaTipoMidia.getTipoMidia() != null ? new TipoMidiaModel(midiaDiversaTipoMidia.getTipoMidia()) : null;
    	this.quantidade = midiaDiversaTipoMidia.getQuantidade();
    	add(linkTo(MidiaParticularController.class).withRel("midias_particulares"));
//    	if(tipoMidia != null)
//    		add(linkTo(methodOn(TipoMidiaRepository.class).findById(tipoMidia.getId())).withSelfRel());
    	if(midiaParticular != null)
    		add(linkTo(methodOn(MidiaParticularController.class).getById(midiaParticular.getId())).withSelfRel());
	}

}
