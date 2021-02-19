package br.com.fattoria.sccm.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.RepresentationModel;

import br.com.fattoria.sccm.controller.EquipamentoController;
import br.com.fattoria.sccm.controller.PesquisaCientificaController;
import br.com.fattoria.sccm.persistence.model.PesquisaCientificaEquipamento;
import lombok.Getter;

@Getter
public class PesquisaCientificaEquipamentosModel extends RepresentationModel<PesquisaCientificaEquipamentosModel> {
	
	private EquipamentoModel equipamento;
	
	private Long valor;
	
	private PesquisaCientificaModel pesquisaCientifica;
	
	public PesquisaCientificaEquipamentosModel(PesquisaCientificaEquipamento pesquisaCientificaEquipamento) {		
		this.pesquisaCientifica             = pesquisaCientificaEquipamento.getPesquisaCientifica() != null ? new PesquisaCientificaModel(pesquisaCientificaEquipamento.getPesquisaCientifica()) : null;
		this.equipamento                    = pesquisaCientificaEquipamento.getEquipamento() != null ? new EquipamentoModel(pesquisaCientificaEquipamento.getEquipamento()) : null;
		this.valor                          = pesquisaCientificaEquipamento.getValor();
		
		if (pesquisaCientificaEquipamento.getPesquisaCientifica() != null && pesquisaCientificaEquipamento.getPesquisaCientifica().getId() != null) {
    		add(linkTo(methodOn(PesquisaCientificaController.class).getById(pesquisaCientificaEquipamento.getPesquisaCientifica().getId())).withRel("pesquisas_cientificas"));
    	}
		
		if (pesquisaCientificaEquipamento.getEquipamento() != null && pesquisaCientificaEquipamento.getEquipamento().getId() != null) {
			add(linkTo(methodOn(EquipamentoController.class).getById(pesquisaCientificaEquipamento.getEquipamento().getId())).withRel("equipamentos"));
		}
		   	
	}

}
