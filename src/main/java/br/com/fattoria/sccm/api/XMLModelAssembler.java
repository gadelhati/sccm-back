package br.com.fattoria.sccm.api;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import br.com.fattoria.sccm.controller.XMLController;
import br.com.fattoria.sccm.persistence.model.XML;

public class XMLModelAssembler extends RepresentationModelAssemblerSupport<XML, XMLModel> {
	
	public XMLModelAssembler() {
		super(XMLController.class, XMLModel.class);
	}

	@Override
	public XMLModel toModel(XML entity) {
		
		XMLModel resource = createResource(entity);
		
		return resource;
	}
	
	XMLModel createResource(XML resource) {
		
		XMLModel model = new XMLModel(resource);

		return model;
	}

	@Override
	public CollectionModel<XMLModel> toCollectionModel(Iterable<? extends XML> entities) {
		return super.toCollectionModel(entities);
	}



}
