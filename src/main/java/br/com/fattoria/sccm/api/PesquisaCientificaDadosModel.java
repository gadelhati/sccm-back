package br.com.fattoria.sccm.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.RepresentationModel;

import br.com.fattoria.sccm.controller.EquipamentoController;
import br.com.fattoria.sccm.controller.PesquisaCientificaController;
import br.com.fattoria.sccm.persistence.model.PesquisaCientificaDados;
import lombok.Getter;

@Getter
public class PesquisaCientificaDadosModel  extends RepresentationModel<PesquisaCientificaDadosModel> {
	
	private TipoDadoModel tipoDado;
	
	private PesquisaCientificaModel pesquisaCientifica;
	
	public PesquisaCientificaDadosModel(PesquisaCientificaDados pesquisaCientificaDado) {		
		this.pesquisaCientifica             = pesquisaCientificaDado.getPesquisaCientifica() != null ? new PesquisaCientificaModel(pesquisaCientificaDado.getPesquisaCientifica()) : null;
		this.tipoDado                       = pesquisaCientificaDado.getTipoDado() != null ? new TipoDadoModel(pesquisaCientificaDado.getTipoDado()) : null;
		
		if (pesquisaCientificaDado.getPesquisaCientifica() != null && pesquisaCientificaDado.getPesquisaCientifica().getId() != null) {
    		add(linkTo(methodOn(PesquisaCientificaController.class).getById(pesquisaCientificaDado.getPesquisaCientifica().getId())).withRel("pesquisas_cientificas"));
    	}
		
		if (pesquisaCientificaDado.getTipoDado() != null && pesquisaCientificaDado.getTipoDado().getId() != null) {
			add(linkTo(methodOn(EquipamentoController.class).getById(pesquisaCientificaDado.getTipoDado().getId())).withRel("tipos_dado"));
		}
		   	
	}

}
