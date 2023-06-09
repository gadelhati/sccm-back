package br.com.fattoria.sccm.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.RepresentationModel;

import br.com.fattoria.sccm.controller.AreaTecnicaController;
import br.com.fattoria.sccm.controller.EquipamentoController;
import br.com.fattoria.sccm.controller.MetodoAmostragemController;
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
	
	public EquipamentoModel(Equipamento equipamento) {		
		this.id = equipamento.getId();
		this.nome = equipamento.getNome();
		this.ativo = equipamento.isAtivo();
		this.sigla = equipamento.getSigla();
		this.codigo = equipamento.getCodigo();
		this.descricao = equipamento.getDescricao();
		this.areaTecnica  = equipamento.getAreaTecnica() != null ? new AreaTecnicaModel(equipamento.getAreaTecnica()) : null;
		this.metodoAmostragem = equipamento.getMetodoAmostragem() != null ? new MetodoAmostragemModel(equipamento.getMetodoAmostragem()) : null;
		
		add(linkTo(methodOn(EquipamentoController.class).getAll()).withRel("equipamentos"));
    	if (equipamento.getAreaTecnica() != null && equipamento.getAreaTecnica() != null) {
    		add(linkTo(methodOn(AreaTecnicaController.class).getById(equipamento.getAreaTecnica().getId())).withRel("areaTecnica"));
    	}
    	
    	if (equipamento != null && equipamento.getMetodoAmostragem() != null) {
    		add(linkTo(methodOn(MetodoAmostragemController.class).getById(equipamento.getMetodoAmostragem().getId())).withRel("metodoAmostragem"));
    	}
    	
    	add(linkTo(methodOn(EquipamentoController.class).getAllTipoDadosByIdEquipamento(equipamento.getId())).withRel("tipo_dados"));
    	add(linkTo(methodOn(EquipamentoController.class).getAllAreaConhecimentoByIdEquipamento(equipamento.getId())).withRel("areas_conhecimento"));
    	
	}

}
