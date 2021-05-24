package br.com.fattoria.sccm.api;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import br.com.fattoria.sccm.controller.SituacaoController;
import br.com.fattoria.sccm.persistence.model.Situacao;

public class SituacaoModelAssembler extends RepresentationModelAssemblerSupport<Situacao, SituacaoModel> {

	public SituacaoModelAssembler() {
		super(SituacaoController.class, SituacaoModel.class);
	}

	@Override
	public SituacaoModel toModel(Situacao entity) {
		
		SituacaoModel resource = createResource(entity);
		
		return resource;
	}
	
	SituacaoModel createResource(Situacao situacao){
		
		SituacaoModel model = new SituacaoModel(situacao);

		return model;
	}

	@Override
	public CollectionModel<SituacaoModel> toCollectionModel(Iterable<? extends Situacao> entities) {
		return super.toCollectionModel(entities);
	}
	
}
