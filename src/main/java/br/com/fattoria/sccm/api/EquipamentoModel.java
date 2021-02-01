package br.com.fattoria.sccm.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.RepresentationModel;

import br.com.fattoria.sccm.controller.AreaTecnicaController;
import br.com.fattoria.sccm.controller.EquipamentoController;
import br.com.fattoria.sccm.controller.MetodoAmostragemController;
import br.com.fattoria.sccm.controller.PaisController;
import br.com.fattoria.sccm.controller.PlataformaController;
import br.com.fattoria.sccm.controller.TipoPlataformaController;
import br.com.fattoria.sccm.controller.UnidadeMedidaController;
import br.com.fattoria.sccm.persistence.model.Equipamento;
import lombok.Getter;

@Getter
public class EquipamentoModel extends RepresentationModel<EquipamentoModel> {
	
	private Long id;
	
	private String nome;

	private boolean ativo;

	private String sigla;

	private String codigo;

	private String descricao;
	
	private AreaTecnicaModel areaTecnica;
	
	private MetodoAmostragemModel metodoAmostragem;
	
	private UnidadeMedidaModel unidadeMedida;
	
	public EquipamentoModel(Equipamento equipamento) {		
		this.id = equipamento.getId();
		this.nome = equipamento.getNome();
		this.ativo = equipamento.isAtivo();
		this.sigla = equipamento.getSigla();
		this.codigo = equipamento.getCodigo();
		this.descricao = equipamento.getDescricao();
		this.areaTecnica  = equipamento.getAreaTecnica() != null ? new AreaTecnicaModel(equipamento.getAreaTecnica()) : null;
		this.metodoAmostragem = equipamento.getMetodoAmostragem() != null ? new MetodoAmostragemModel(equipamento.getMetodoAmostragem()) : null;
		this.unidadeMedida = equipamento.getUnidadeMedida() != null ? new UnidadeMedidaModel(equipamento.getUnidadeMedida()) : null;
		
		add(linkTo(EquipamentoController.class).withRel("equipamento"));
    	if (equipamento.getAreaTecnica() != null && equipamento.getAreaTecnica().getId() != null) {
    		add(linkTo(methodOn(AreaTecnicaController.class).getById(equipamento.getAreaTecnica().getId())).withRel("areaTecnica"));
    	}
    	
    	if (equipamento != null && equipamento.getMetodoAmostragem().getId() != null) {
    		add(linkTo(methodOn(MetodoAmostragemController.class).getById(equipamento.getMetodoAmostragem().getId())).withRel("metodoAmostragem"));
    	}
    	
    	if (equipamento != null && equipamento.getUnidadeMedida().getId() != null) {    	
    		add(linkTo(methodOn(UnidadeMedidaController.class).getById(equipamento.getUnidadeMedida().getId())).withRel("unidadeMedida"));
    	}
	}

}