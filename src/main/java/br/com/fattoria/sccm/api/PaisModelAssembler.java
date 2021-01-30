package br.com.fattoria.sccm.api;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import br.com.fattoria.sccm.controller.PaisController;
import br.com.fattoria.sccm.persistence.model.Pais;

public class PaisModelAssembler extends RepresentationModelAssemblerSupport<Pais, PaisModel> {

	public PaisModelAssembler() {
		super(PaisController.class, PaisModel.class);
	}

	@Override
	public PaisModel toModel(Pais pais) {

		PaisModel resource = createResource(pais);
		return resource;
	}
	
	PaisModel createResource(Pais pais){
		
		PaisModel plataformaModel = new PaisModel(pais);

		return plataformaModel;
	}

	@Override
	public CollectionModel<PaisModel> toCollectionModel(Iterable<? extends Pais> entities) {
		return super.toCollectionModel(entities);
	}

}