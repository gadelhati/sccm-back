package br.com.fattoria.sccm.api;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import br.com.fattoria.sccm.controller.PlataformaController;
import br.com.fattoria.sccm.persistence.model.Plataforma;

public class PlataformaModelAssembler extends RepresentationModelAssemblerSupport<Plataforma, PlataformaModel> {

	public PlataformaModelAssembler() {
		super(PlataformaController.class, PlataformaModel.class);
	}

	@Override
	public PlataformaModel toModel(Plataforma plataforma) {

		PlataformaModel resource = createResource(plataforma);
		return resource;
	}
	
	PlataformaModel createResource(Plataforma plataforma){
		
		PlataformaModel plataformaModel = new PlataformaModel(plataforma);

		return plataformaModel;
	}

	@Override
	public CollectionModel<PlataformaModel> toCollectionModel(Iterable<? extends Plataforma> entities) {
		return super.toCollectionModel(entities);
	}

}
