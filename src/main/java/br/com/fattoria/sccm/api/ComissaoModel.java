package br.com.fattoria.sccm.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.Calendar;

import org.springframework.hateoas.RepresentationModel;

import br.com.fattoria.sccm.controller.DestinoController;
import br.com.fattoria.sccm.persistence.model.Comissao;
import br.com.fattoria.sccm.persistence.model.Destino;
import lombok.Getter;

@Getter
public class ComissaoModel extends RepresentationModel<ComissaoModel> {

	private Long id;	
	
	private String numeroPC;
	
	private String nomeComissao;
	
	private String nomeCruzeiro;
	
	private Integer pernada;
	
	private Calendar dataEntrada;
	
	private Calendar dataOficial;
	
	private Calendar dataInicio;
	
	private Calendar dataFim;
	
	private String observacoes;
	
	private String imagem;
	
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
		
		add(linkTo(DestinoController.class).withRel("comissao"));
    	    	
	}
	

	
}
