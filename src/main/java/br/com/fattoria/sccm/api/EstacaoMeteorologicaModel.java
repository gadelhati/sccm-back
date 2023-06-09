package br.com.fattoria.sccm.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.hateoas.RepresentationModel;

import br.com.fattoria.sccm.controller.DestinoController;
import br.com.fattoria.sccm.persistence.model.EstacaoMeteorologica;
import lombok.Getter;

@Getter
public class EstacaoMeteorologicaModel extends RepresentationModel<EstacaoMeteorologicaModel>{

	private Long id;
	
	private Long codigo;
	
	private String nome;
	
	private String latitude;
	
	private String longitude;
	
	private boolean ativo;
	
	public EstacaoMeteorologicaModel(EstacaoMeteorologica estacoes) {		
		this.id = estacoes.getId();
		this.codigo = estacoes.getCodigo();
		this.nome = estacoes.getNome();
		this.latitude = estacoes.getLatitude();
		this.longitude = estacoes.getLongitude();
		this.ativo = estacoes.isAtivo();
		
		add(linkTo(DestinoController.class).withRel("estacoes_meteorologicas"));
    	    	
	}
	
}
