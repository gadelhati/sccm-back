package br.com.fattoria.sccm.api;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import br.com.fattoria.sccm.controller.EstacaoMeteorologicaController;
import br.com.fattoria.sccm.persistence.model.EstacaoMeteorologica;

public class EstacaoMeteorologicaModelAssembler extends RepresentationModelAssemblerSupport<EstacaoMeteorologica, EstacaoMeteorologicaModel> {

	public EstacaoMeteorologicaModelAssembler() {
		super(EstacaoMeteorologicaController.class, EstacaoMeteorologicaModel.class);
	}

	@Override
	public EstacaoMeteorologicaModel toModel(EstacaoMeteorologica entity) {
		
		EstacaoMeteorologicaModel resource = createResource(entity);
		
		return resource;
	}
	
	EstacaoMeteorologicaModel createResource(EstacaoMeteorologica estacaoMeteorologica){
		
		EstacaoMeteorologicaModel model = new EstacaoMeteorologicaModel(estacaoMeteorologica);

		return model;
	}

	@Override
	public CollectionModel<EstacaoMeteorologicaModel> toCollectionModel(Iterable<? extends EstacaoMeteorologica> entities) {
		return super.toCollectionModel(entities);
	}
	
}
