package br.com.fattoria.sccm.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.springframework.hateoas.RepresentationModel;

import br.com.fattoria.sccm.controller.MidiaDiversaController;
import br.com.fattoria.sccm.persistence.model.MidiaDiversa;
import lombok.Getter;

@Getter
public class MidiaDiversaModel extends RepresentationModel<MidiaDiversaModel> {
	
	private Long id;
	
	private String conteudo;
	
	private String dataFormatada;
	
	private Calendar data;
	
	private boolean documento;
	
	private boolean backup;

    public MidiaDiversaModel(MidiaDiversa midia) {
    	
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY", Locale.forLanguageTag("pt-BR"));
    	
    	this.id = midia.getId();
    	this.conteudo = midia.getConteudo();
    	this.dataFormatada = midia.getData() != null ? dateFormat.format(midia.getData().getTime()) : "";
    	this.data = midia.getData();
    	this.documento = midia.isDocumento();
    	this.backup = midia.isBackup();
    	add(linkTo(MidiaDiversaController.class).withRel("midias_diversas"));
        add(linkTo(methodOn(MidiaDiversaController.class).getById(midia.getId())).withSelfRel());
        add(linkTo(methodOn(MidiaDiversaController.class).getAllMidiaDiversaTipoMidiaByIdMidiaDiversa(midia.getId())).withSelfRel());
	}

}
