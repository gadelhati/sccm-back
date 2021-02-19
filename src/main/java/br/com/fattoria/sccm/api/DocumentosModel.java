package br.com.fattoria.sccm.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Calendar;

import org.springframework.hateoas.RepresentationModel;

import br.com.fattoria.sccm.controller.DocumentosController;
import br.com.fattoria.sccm.persistence.model.Destino;
import br.com.fattoria.sccm.persistence.model.Documento;
import lombok.Getter;

@Getter
public class DocumentosModel extends RepresentationModel<DocumentosModel> {

	private Long id;
	
	private String anexo;
	
	private Calendar dataRecebimento;
	
	private TipoAnexoModel tipoAnexo;
	
	private Destino destino;
	
	private String observacoes;
	
	public DocumentosModel(Documento documento) {		
		this.id = documento.getId();
		this.anexo = documento.getAnexo();
		this.dataRecebimento = documento.getDataRecebimento();
		this.tipoAnexo = documento.getTipoAnexo() != null ? new TipoAnexoModel(documento.getTipoAnexo()) : null;
		this.destino = documento.getDestino();
		this.observacoes = documento.getObservacoes();
		
		add(linkTo(DocumentosController.class).withRel("documentos"));
		
		if (documento.getTipoAnexo() != null && documento.getTipoAnexo().getId() != null) {
			add(linkTo(methodOn(DocumentosController.class).getById(documento.getTipoAnexo().getId())).withRel("tipos_anexos"));			
		}
    	    	
	}
}
