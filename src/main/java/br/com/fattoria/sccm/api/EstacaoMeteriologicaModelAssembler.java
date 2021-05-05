package br.com.fattoria.sccm.api;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import br.com.fattoria.sccm.controller.EstacaoMeteriologicasController;
import br.com.fattoria.sccm.persistence.model.EstacaoMeteriologica;

public class EstacaoMeteriologicaModelAssembler extends RepresentationModelAssemblerSupport<EstacaoMeteriologica, EstacaoMeteriologicaModel> {

	public EstacaoMeteriologicaModelAssembler() {
		super(EstacaoMeteriologicasController.class, EstacaoMeteriologicaModel.class);
	}

	@Override
	public EstacaoMeteriologicaModel toModel(EstacaoMeteriologica entity) {
		
		EstacaoMeteriologicaModel resource = createResource(entity);
		
		return resource;
	}
	
	EstacaoMeteriologicaModel createResource(EstacaoMeteriologica estacaoMeteorologica){
		
		EstacaoMeteriologicaModel model = new EstacaoMeteriologicaModel(estacaoMeteorologica);

		return model;
	}

	@Override
	public CollectionModel<EstacaoMeteriologicaModel> toCollectionModel(Iterable<? extends EstacaoMeteriologica> entities) {
		return super.toCollectionModel(entities);
	}
	
}
