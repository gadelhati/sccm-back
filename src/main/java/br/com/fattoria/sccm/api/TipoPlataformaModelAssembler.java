package br.com.fattoria.sccm.api;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import br.com.fattoria.sccm.controller.TipoPlataformaController;
import br.com.fattoria.sccm.persistence.model.TipoPlataforma;

public class TipoPlataformaModelAssembler extends RepresentationModelAssemblerSupport<TipoPlataforma, TipoPlataformaModel> {

	public TipoPlataformaModelAssembler() {
		super(TipoPlataformaController.class, TipoPlataformaModel.class);
	}

	@Override
	public TipoPlataformaModel toModel(TipoPlataforma tipoPlataforma) {

		TipoPlataformaModel resource = createResource(tipoPlataforma);
		return resource;
	}
	
	TipoPlataformaModel createResource(TipoPlataforma tipoPlataforma){
		
		TipoPlataformaModel tipoPlataformaModel = new TipoPlataformaModel(tipoPlataforma);

		return tipoPlataformaModel;
	}

	@Override
	public CollectionModel<TipoPlataformaModel> toCollectionModel(Iterable<? extends TipoPlataforma> entities) {
		return super.toCollectionModel(entities);
	}

}