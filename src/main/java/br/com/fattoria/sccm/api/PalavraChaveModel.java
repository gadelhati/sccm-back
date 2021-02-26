package br.com.fattoria.sccm.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.RepresentationModel;

import br.com.fattoria.sccm.controller.DestinoController;
import br.com.fattoria.sccm.controller.PesquisaCientificaController;
import br.com.fattoria.sccm.persistence.model.PalavraChave;
import lombok.Getter;

@Getter
public class PalavraChaveModel extends RepresentationModel<ComissaoModel> {
	
	private Long id;
	
	private String palavraChave;
	
	private PesquisaCientificaModel pesquisaCientifica;
	
	public PalavraChaveModel(PalavraChave palavraChave) {		
		this.id           = palavraChave.getId();
		this.palavraChave = palavraChave.getPalavraChave();
		this.pesquisaCientifica = palavraChave.getPesquisaCientifica() != null ? new PesquisaCientificaModel(palavraChave.getPesquisaCientifica()) : null;
		
		add(linkTo(DestinoController.class).withRel("palavraChave"));
		
		if (palavraChave.getPesquisaCientifica() != null && palavraChave.getPesquisaCientifica().getId() != null) {
			add(linkTo(methodOn(PesquisaCientificaController.class).getById(palavraChave.getPesquisaCientifica().getId())).withRel("pesquisaCientifica"));
		}
		    	
	}
	
	
}
