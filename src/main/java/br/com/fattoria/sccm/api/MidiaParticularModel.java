package br.com.fattoria.sccm.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Calendar;

import org.springframework.hateoas.RepresentationModel;

import br.com.fattoria.sccm.controller.MidiaParticularController;
import br.com.fattoria.sccm.persistence.model.MidiaParticular;
import lombok.Getter;

@Getter
public class MidiaParticularModel extends RepresentationModel<MidiaParticularModel> {
	
	private Long id;
	
	private String comissaoProjeto;
	
	private Long numeroAutorizacao;
	
	private Long numeroRA;
	
	private String numeroFicha;
	
	private Long codigoC;
	
	private Calendar data;
	
	private PlataformaModel plataforma;
	
	private InstituicaoModel instituicao;
	
	private SituacaoModel situacao;
	
	private String observacoes;

    public MidiaParticularModel(MidiaParticular midia) {

    	this.id = midia.getId();
    	this.comissaoProjeto = midia.getComissaoProjeto();
    	this.numeroAutorizacao = midia.getNumeroAutorizacao();
    	this.numeroRA = midia.getNumeroRA();
    	this.numeroFicha = midia.getNumeroFicha();
    	this.codigoC = midia.getCodigoC();
    	this.data = midia.getData();
    	this.plataforma = midia.getPlataforma() != null ? new PlataformaModel(midia.getPlataforma()) : null;
    	this.instituicao = midia.getInstituicao() != null ? new InstituicaoModel(midia.getInstituicao()) : null;
    	this.observacoes = midia.getObservacoes();
    	this.situacao = midia.getSituacao() != null ? new SituacaoModel(midia.getSituacao()) : null;
    	add(linkTo(MidiaParticularController.class).withRel("midias_particulares"));
        add(linkTo(methodOn(MidiaParticularController.class).getById(midia.getId())).withSelfRel());
        add(linkTo(methodOn(MidiaParticularController.class).getAllMidiaParticularTipoMidiaByIdMidiaParticular(midia.getId())).withSelfRel());
	}

}
