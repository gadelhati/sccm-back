package br.com.fattoria.sccm.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Calendar;

import org.springframework.hateoas.RepresentationModel;

import br.com.fattoria.sccm.controller.ComissaoController;
import br.com.fattoria.sccm.controller.EstacaoMeteorologicasController;
import br.com.fattoria.sccm.controller.PesquisaCientificaController;
import br.com.fattoria.sccm.controller.PlataformaController;
import br.com.fattoria.sccm.controller.ShipSynopController;
import br.com.fattoria.sccm.controller.SituacaoController;
import br.com.fattoria.sccm.persistence.model.ShipSynop;
import lombok.Getter;

@Getter
public class ShipSynopModel extends RepresentationModel<ShipSynopModel> {

	private Long id;

	private PlataformaModel plataforma;

	private ComissaoModel comissao;

	private Calendar periodoInicial;

	private Calendar periodoFinal;

	private PesquisaCientificaModel pesquisaCientifica;

	private String protocolo;

	private Integer numeroModelos;

	private String dados;

	private Integer numeroInformacoes;

	private Boolean corrigido;

	private Calendar dataEntrada;

	private Calendar dataSaida;

	private String observacoes;
	
	private SituacaoModel situacao;
	
	private EstacaoMeteorologicaModel estacaoMeteorologica;

	public ShipSynopModel(ShipSynop shipSynop) {
		this.id = shipSynop.getId();
		this.plataforma = shipSynop.getPlataforma() != null ? new PlataformaModel(shipSynop.getPlataforma()) : null;
		this.comissao = shipSynop.getComissao() != null ? new ComissaoModel(shipSynop.getComissao()) : null;
		this.periodoInicial = shipSynop.getPeriodoInicial();
		this.periodoFinal = shipSynop.getPeriodoFinal();
		this.pesquisaCientifica = shipSynop.getPesquisaCientifica() != null ? new PesquisaCientificaModel(shipSynop.getPesquisaCientifica()) : null;
		this.protocolo = shipSynop.getProtocolo();
		this.numeroModelos = shipSynop.getNumeroModelos();
		this.dados = shipSynop.getDados();
		this.numeroInformacoes = shipSynop.getNumeroInformacoes();
		this.corrigido = shipSynop.getCorrigido();
		this.dataEntrada = shipSynop.getDataEntrada();
		this.dataSaida = shipSynop.getDataSaida();
		this.observacoes = shipSynop.getObservacoes();
		this.situacao = shipSynop.getSituacao() != null ? new SituacaoModel(shipSynop.getSituacao()) : null;
		this.estacaoMeteorologica = shipSynop.getEstacaoMeteorologica() != null ? new EstacaoMeteorologicaModel(shipSynop.getEstacaoMeteorologica()) : null;
		
		add(linkTo(methodOn(ShipSynopController.class).getAllShip()).withRel("ship"));
		
		add(linkTo(methodOn(ShipSynopController.class).getAllSynop()).withRel("synop"));
		
		if (shipSynop.getPlataforma() != null && shipSynop.getPlataforma().getId() != null) {
    		add(linkTo(methodOn(PlataformaController.class).getById(shipSynop.getPlataforma().getId())).withRel("plataforma"));
    	}
		
		if (shipSynop.getComissao() != null && shipSynop.getComissao().getId() != null) {
    		add(linkTo(methodOn(ComissaoController.class).getById(shipSynop.getComissao().getId())).withRel("comissao"));
    	}
		
		if (shipSynop.getPesquisaCientifica() != null && shipSynop.getPesquisaCientifica().getId() != null) {
    		add(linkTo(methodOn(PesquisaCientificaController.class).getById(shipSynop.getPesquisaCientifica().getId())).withRel("pesquisaCientifica"));
    	}
		
		if (shipSynop.getSituacao() != null && shipSynop.getSituacao().getId() != null) {
    		add(linkTo(methodOn(SituacaoController.class).getById(shipSynop.getSituacao().getId())).withRel("situacao"));
    	}
		
		if (shipSynop.getEstacaoMeteorologica() != null && shipSynop.getEstacaoMeteorologica().getId() != null) {
    		add(linkTo(methodOn(EstacaoMeteorologicasController.class).getById(shipSynop.getEstacaoMeteorologica().getId())).withRel("estacaoMeteorologica"));
    	}
		
	}
	
	
	
}
