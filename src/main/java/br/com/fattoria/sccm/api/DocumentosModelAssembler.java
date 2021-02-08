package br.com.fattoria.sccm.api;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import br.com.fattoria.sccm.controller.DocumentosController;
import br.com.fattoria.sccm.persistence.model.Documento;

public class DocumentosModelAssembler extends RepresentationModelAssemblerSupport<Documento, DocumentosModel> {



	public DocumentosModelAssembler() {
		super(DocumentosController.class, DocumentosModel.class);
	}

	@Override
	public DocumentosModel toModel(Documento entity) {
		
		DocumentosModel resource = createResource(entity);
		
		return resource;
	}
	
	DocumentosModel createResource(Documento documentos){
		
		DocumentosModel model = new DocumentosModel(documentos);

		return model;
	}

	@Override
	public CollectionModel<DocumentosModel> toCollectionModel(Iterable<? extends Documento> entities) {
		return super.toCollectionModel(entities);
	}
	

	
}
