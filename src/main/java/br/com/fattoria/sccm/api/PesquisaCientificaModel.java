package br.com.fattoria.sccm.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Calendar;

import org.springframework.hateoas.RepresentationModel;

import br.com.fattoria.sccm.controller.ComissaoController;
import br.com.fattoria.sccm.controller.InstituicaoController;
import br.com.fattoria.sccm.controller.PesquisaCientificaController;
import br.com.fattoria.sccm.controller.PlataformaController;
import br.com.fattoria.sccm.persistence.model.PesquisaCientifica;
import br.com.fattoria.sccm.persistence.model.Sigilo;
import lombok.Getter;

@Getter
public class PesquisaCientificaModel  extends RepresentationModel<PesquisaCientificaModel> {
	
	private Long id;	

	private Sigilo sigilo;

	private ComissaoModel comissao;

	private InstituicaoModel instituicao;

	private PlataformaModel plataforma;

	private Calendar dataCadastro;

	private String comandante;
	
	private String chefeCientifico;
	
	private String cartaNautica;
	
	private String limiteNorteLatitude;
	
	private String limiteSulLatitude;
	
	private String limiteLesteLongitude;
	
	private String limiteOesteLongitude;
	
	private String numeroPC;
	
	public PesquisaCientificaModel(PesquisaCientifica pesquisaCientifica) {		
		this.id                   = pesquisaCientifica.getId();
		this.numeroPC             = pesquisaCientifica.getNumeroPC();
		this.sigilo               = pesquisaCientifica.getSigilo(); 
		this.comissao             = pesquisaCientifica.getComissao() != null ? new ComissaoModel(pesquisaCientifica.getComissao()) : null;
		this.instituicao          = pesquisaCientifica.getInstituicao() != null ? new InstituicaoModel(pesquisaCientifica.getInstituicao()) : null;
		this.plataforma           = pesquisaCientifica.getPlataforma() != null ? new PlataformaModel(pesquisaCientifica.getPlataforma()) : null;
		this.dataCadastro         = pesquisaCientifica.getDataCadastro();
		this.comandante           = pesquisaCientifica.getComandante();
		this.chefeCientifico      = pesquisaCientifica.getChefeCientifico();
		this.cartaNautica         = pesquisaCientifica.getCartaNautica();
		this.limiteNorteLatitude  = pesquisaCientifica.getLimiteNorteLatitude();
		this.limiteSulLatitude    = pesquisaCientifica.getLimiteSulLatitude();
		this.limiteLesteLongitude = pesquisaCientifica.getLimiteLesteLongitude();
		this.limiteOesteLongitude = pesquisaCientifica.getLimiteOesteLongitude();
		
		add(linkTo(PesquisaCientificaController.class).withRel("pesquisas_cientificas"));
		
		if (pesquisaCientifica.getComissao() != null && pesquisaCientifica.getComissao().getId() != null) {
    		add(linkTo(methodOn(ComissaoController.class).getById(pesquisaCientifica.getComissao().getId())).withRel("comissao"));
    	}
		
		if (pesquisaCientifica.getInstituicao() != null && pesquisaCientifica.getInstituicao().getId() != null) {
			add(linkTo(methodOn(InstituicaoController.class).getById(pesquisaCientifica.getInstituicao().getId())).withRel("instituicoes"));
		}
		
		if (pesquisaCientifica.getPlataforma() != null && pesquisaCientifica.getPlataforma().getId() != null) {
			add(linkTo(methodOn(PlataformaController.class).getById(pesquisaCientifica.getPlataforma().getId())).withRel("plataformas"));
		}
    	    	
	}

}
