package br.com.fattoria.sccm.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.RepresentationModel;

import br.com.fattoria.sccm.controller.XMLController;
import br.com.fattoria.sccm.persistence.model.XML;
import lombok.Getter;

@Getter
public class XMLModel extends RepresentationModel<XMLModel> {

	private Long id;
	
	private String nome;
	
	private String xml;

	public XMLModel(XML xml) {		
		this.id = xml.getId();
		this.nome = xml.getNome();
		this.xml = xml.getXml();
		
		add(linkTo(methodOn(XMLController.class).getAll()).withRel("xml"));
		
	}
	
	
	
}
