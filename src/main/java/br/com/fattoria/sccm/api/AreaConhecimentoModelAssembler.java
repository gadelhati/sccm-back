package br.com.fattoria.sccm.api;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import br.com.fattoria.sccm.controller.AreaConhecimentoController;
import br.com.fattoria.sccm.persistence.model.AreaConhecimento;

public class AreaConhecimentoModelAssembler extends RepresentationModelAssemblerSupport<AreaConhecimento, AreaConhecimentoModel> {

	public AreaConhecimentoModelAssembler() {
		super(AreaConhecimentoController.class, AreaConhecimentoModel.class);
	}

	@Override
	public AreaConhecimentoModel toModel(AreaConhecimento areaConhecimento) {

		AreaConhecimentoModel resource = createResource(areaConhecimento);
		
		return resource;
	}
	
	AreaConhecimentoModel createResource(AreaConhecimento areaConhecimento){
		
		AreaConhecimentoModel model = new AreaConhecimentoModel(areaConhecimento);

		return model;
	}

	@Override
	public CollectionModel<AreaConhecimentoModel> toCollectionModel(Iterable<? extends AreaConhecimento> entities) {
		return super.toCollectionModel(entities);
	}
	
}
