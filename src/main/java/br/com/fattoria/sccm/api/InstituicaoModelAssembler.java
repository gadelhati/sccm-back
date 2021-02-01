package br.com.fattoria.sccm.api;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import br.com.fattoria.sccm.controller.InstituicaoController;
import br.com.fattoria.sccm.persistence.model.Instituicao;

public class InstituicaoModelAssembler  extends RepresentationModelAssemblerSupport<Instituicao, InstituicaoModel> {

	
	public InstituicaoModelAssembler() {		
		super(InstituicaoController.class, InstituicaoModel.class);
	}

	@Override
	public InstituicaoModel toModel(Instituicao entity) {
		
		InstituicaoModel resource = createResource(entity);
		
		return resource;
	}
	
	InstituicaoModel createResource(Instituicao instituicao){
		
		InstituicaoModel  model = new InstituicaoModel(instituicao);

		return model;
	}

	@Override
	public CollectionModel<InstituicaoModel> toCollectionModel(Iterable<? extends Instituicao> entities) {
		return super.toCollectionModel(entities);
	}
	
}
