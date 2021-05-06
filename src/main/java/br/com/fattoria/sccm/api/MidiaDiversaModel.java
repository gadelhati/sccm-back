package br.com.fattoria.sccm.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Calendar;

import org.springframework.hateoas.RepresentationModel;

import br.com.fattoria.sccm.controller.MidiaDiversaController;
import br.com.fattoria.sccm.persistence.model.MidiaDiversa;
import lombok.Getter;

@Getter
public class MidiaDiversaModel extends RepresentationModel<MidiaDiversaModel> {
	
	private Long id;
	
	private String conteudo;
	
	private Calendar data;
	
	private boolean documento;
	
	private boolean backup;
	
	private String numeroOficio;
	
	private String numeroH;
	
	private Long codigo;

    public MidiaDiversaModel(MidiaDiversa midia) {

    	this.id = midia.getId();
    	this.conteudo = midia.getConteudo();
    	this.data = midia.getData();
    	this.documento = midia.isDocumento();
    	this.backup = midia.isBackup();
    	this.numeroOficio = midia.getNumeroOficio();
    	this.numeroH = midia.getNumeroH();
    	this.codigo = midia.getCodigo();
    	add(linkTo(MidiaDiversaController.class).withRel("midias_diversas"));
        add(linkTo(methodOn(MidiaDiversaController.class).getById(midia.getId())).withSelfRel());
        add(linkTo(methodOn(MidiaDiversaController.class).getAllMidiaDiversaTipoMidiaByIdMidiaDiversa(midia.getId())).withSelfRel());
	}

}
