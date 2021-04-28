package br.com.fattoria.sccm.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Calendar;

import org.springframework.hateoas.RepresentationModel;

import br.com.fattoria.sccm.controller.ControleInternoController;
import br.com.fattoria.sccm.controller.InstituicaoController;
import br.com.fattoria.sccm.controller.PesquisaCientificaController;
import br.com.fattoria.sccm.persistence.model.ControleInterno;
import lombok.Getter;

@Getter
public class ControleInternoModel extends RepresentationModel<ControleInternoModel> {

	private Long id;
	
	private String numeroOficio;
	
	private InstituicaoModel instituicao;
	
	private String arquivoTecnico;
	
	private String formaEnvio;
	
	private String numeroAutorizacao;
	
	private PesquisaCientificaModel pesquisaCientifica;
	
	private Calendar dataOficio;
	
	private String recibo;

	public ControleInternoModel(ControleInterno controleInterno) {
		this.id = controleInterno.getId();
		this.numeroOficio = controleInterno.getNumeroOficio();
		this.instituicao = controleInterno.getInstituicao() != null ? new InstituicaoModel(controleInterno.getInstituicao()) : null;
		this.arquivoTecnico = controleInterno.getArquivoTecnico();
		this.formaEnvio = controleInterno.getFormaEnvio();
		this.numeroAutorizacao = controleInterno.getNumeroAutorizacao();
		this.pesquisaCientifica = controleInterno.getPesquisaCientifica() != null ? new PesquisaCientificaModel(controleInterno.getPesquisaCientifica()) : null;
		this.dataOficio = controleInterno.getDataOficio();
		this.recibo = controleInterno.getRecibo();
		
		add(linkTo(methodOn(ControleInternoController.class).getAll()).withRel("controleInterno"));

		if (controleInterno.getInstituicao() != null && controleInterno.getInstituicao() != null) {
    		add(linkTo(methodOn(InstituicaoController.class).getById(controleInterno.getInstituicao().getId())).withRel("instituicao"));
    	}
		
		if (controleInterno.getPesquisaCientifica() != null && controleInterno.getPesquisaCientifica() != null) {
    		add(linkTo(methodOn(PesquisaCientificaController.class).getById(controleInterno.getPesquisaCientifica().getId())).withRel("pesquisaCientifica"));
    	}
		
	}
	
	

	
}
