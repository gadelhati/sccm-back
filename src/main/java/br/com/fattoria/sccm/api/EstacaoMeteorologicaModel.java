package br.com.fattoria.sccm.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.hateoas.RepresentationModel;

import br.com.fattoria.sccm.controller.DestinoController;
import br.com.fattoria.sccm.persistence.model.EstacaoMeteorologica;
import lombok.Getter;

@Getter
public class EstacaoMeteorologicaModel extends RepresentationModel<EstacaoMeteorologicaModel>{

	private Long id;
	
	private String nome;
	
	private String latitude;
	
	private String longitude;
	
	public EstacaoMeteorologicaModel(EstacaoMeteorologica estacoes) {		
		this.id = estacoes.getId();
		this.nome = estacoes.getNome();
		this.latitude = estacoes.getLatitude();
		this.longitude = estacoes.getLongitude();
		
		add(linkTo(DestinoController.class).withRel("estacoes_meteorologicas"));
    	    	
	}
	
}
