package br.com.fattoria.sccm.api;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import br.com.fattoria.sccm.controller.AreaTecnicaController;
import br.com.fattoria.sccm.persistence.model.AreaTecnica;

public class AreaTecnicaModelAssembler extends RepresentationModelAssemblerSupport<AreaTecnica, AreaTecnicaModel> {

	public AreaTecnicaModelAssembler() {
		super(AreaTecnicaController.class, AreaTecnica.class);		
	}

	@Override
	public AreaTecnicaModel toModel(AreaTecnica entity) {

		AreaTecnicaModel resource = createResource(entity);
		
		return resource;
	}
	
	AreaTecnicaModel createResource(AreaTecnica areaTecnica) {
		
		AreaTecnicaModel equipamentoModel = new AreaTecnicaModel(areaTecnica);

		return equipamentoModel;
	}

	@Override
	public CollectionModel<AreaTecnicaModel> toCollectionModel(Iterable<? extends AreaTecnica> entities) {
		return super.toCollectionModel(entities);
	}

}
