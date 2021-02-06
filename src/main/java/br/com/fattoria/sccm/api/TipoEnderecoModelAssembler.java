package br.com.fattoria.sccm.api;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import br.com.fattoria.sccm.controller.TipoEnderecoController;
import br.com.fattoria.sccm.persistence.model.TipoEndereco;

public class TipoEnderecoModelAssembler extends RepresentationModelAssemblerSupport<TipoEndereco, TipoEnderecoModel> {

	public TipoEnderecoModelAssembler() {
		super(TipoEnderecoController.class, TipoEnderecoModel.class);
	}

	@Override
	public TipoEnderecoModel toModel(TipoEndereco tipoEndereco) {

		TipoEnderecoModel resource = createResource(tipoEndereco);
		return resource;
	}
	
	TipoEnderecoModel createResource(TipoEndereco tipoEndereco){
		
		TipoEnderecoModel model = new TipoEnderecoModel(tipoEndereco);

		return model;
	}

	@Override
	public CollectionModel<TipoEnderecoModel> toCollectionModel(Iterable<? extends TipoEndereco> entities) {
		return super.toCollectionModel(entities);
	}

	
}
