package br.com.fattoria.sccm.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Calendar;

import org.springframework.hateoas.RepresentationModel;

import br.com.fattoria.sccm.controller.DocumentosController;
import br.com.fattoria.sccm.controller.InstituicaoController;
import br.com.fattoria.sccm.persistence.model.Destino;
import br.com.fattoria.sccm.persistence.model.Documento;
import br.com.fattoria.sccm.persistence.model.TipoAnexo;
import lombok.Getter;

@Getter
public class DocumentosModel extends RepresentationModel<DocumentosModel> {

	private Long id;
	
	private String anexo;
	
	private Calendar dataRecebimento;
	
	private TipoAnexoModel tipoAnexo;
	
	private Destino destino;
	
	public DocumentosModel(Documento documentos) {		
		this.id = documentos.getId();
		this.anexo = documentos.getAnexo();
		this.dataRecebimento = documentos.getDataRecebimento();
		this.tipoAnexo = documentos.getTipoAnexo() != null ? new TipoAnexoModel(documentos.getTipoAnexo()) : null;
		this.destino= documentos.getDestino();
		
		add(linkTo(DocumentosController.class).withRel("documentos"));
		
		if (documentos.getTipoAnexo() != null && documentos.getTipoAnexo().getId() != null) {
			add(linkTo(methodOn(DocumentosController.class).getById(documentos.getTipoAnexo().getId())).withRel("tipoAnexo"));			
		}
    	    	
	}
}
