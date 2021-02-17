package br.com.fattoria.sccm.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.Calendar;

import org.springframework.hateoas.RepresentationModel;

import br.com.fattoria.sccm.controller.ComissaoController;
import br.com.fattoria.sccm.persistence.model.Comissao;
import lombok.Getter;

@Getter
public class ComissaoModel extends RepresentationModel<ComissaoModel> {

	private Long id;	
	
	private String nomeComissao;
	
	private String nomeCruzeiro;
	
	private Integer pernada;
	
	private Calendar dataEntrada;
	
	private Calendar dataOficial;
	
	private Calendar dataInicio;
	
	private Calendar dataFim;
	
	private String observacoes;
	
	private String imagem;
	
	private TipoComissaoModel tipoComissao;
	
	public ComissaoModel(Comissao comissao) {		
		this.id           = comissao.getId();		
		this.nomeComissao = comissao.getNomeComissao();
		this.nomeCruzeiro = comissao.getNomeCruzeiro();
		this.pernada      = comissao.getPernada();
		this.dataEntrada  = comissao.getDataEntrada();
		this.dataOficial  = comissao.getDataOficial();
		this.dataInicio   = comissao.getDataInicio();
		this.dataFim      = comissao.getDataFim();
		this.observacoes  = comissao.getObservacoes();
		this.imagem       = comissao.getImagem();
		this.tipoComissao = comissao.getTipoComissao() != null ? new TipoComissaoModel(comissao.getTipoComissao()) : null;
		
		add(linkTo(ComissaoController.class).withRel("comissoes"));
    	    	
	}
	

	
}
