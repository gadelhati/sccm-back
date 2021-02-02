package br.com.fattoria.sccm.api;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import br.com.fattoria.sccm.controller.TipoDadoController;
import br.com.fattoria.sccm.persistence.model.TipoDado;

public class TipoDadoModelAssembler extends RepresentationModelAssemblerSupport<TipoDado, TipoDadoModel> {

	public TipoDadoModelAssembler() {
		super(TipoDadoController.class, TipoDadoModel.class);
	}

	@Override
	public TipoDadoModel toModel(TipoDado tipoDado) {

		TipoDadoModel resource = createResource(tipoDado);
		return resource;
	}
	
	TipoDadoModel createResource(TipoDado tipoDado){
		
		TipoDadoModel tipoDadoModel = new TipoDadoModel(tipoDado);

		return tipoDadoModel;
	}

	@Override
	public CollectionModel<TipoDadoModel> toCollectionModel(Iterable<? extends TipoDado> entities) {
		return super.toCollectionModel(entities);
	}

}
