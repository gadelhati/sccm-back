package br.com.fattoria.sccm.api;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import br.com.fattoria.sccm.controller.DocumentosController;
import br.com.fattoria.sccm.persistence.model.Documentos;

public class DocumentosModelAssembler extends RepresentationModelAssemblerSupport<Documentos, DocumentosModel> {



	public DocumentosModelAssembler() {
		super(DocumentosController.class, DocumentosModel.class);
	}

	@Override
	public DocumentosModel toModel(Documentos entity) {
		
		DocumentosModel resource = createResource(entity);
		
		return resource;
	}
	
	DocumentosModel createResource(Documentos documentos){
		
		DocumentosModel model = new DocumentosModel(documentos);

		return model;
	}

	@Override
	public CollectionModel<DocumentosModel> toCollectionModel(Iterable<? extends Documentos> entities) {
		return super.toCollectionModel(entities);
	}
	

	
}
