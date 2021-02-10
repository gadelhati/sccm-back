package br.com.fattoria.sccm.api;

import org.springframework.hateoas.RepresentationModel;

import br.com.fattoria.sccm.persistence.model.TipoComissao;
import lombok.Getter;

@Getter
public class TipoComissaoModel extends RepresentationModel<TipoComissaoModel> {

	private Long id;	
	
	private String descricao;
	
	public TipoComissaoModel(TipoComissao tipoComissao) {		
		this.id           = tipoComissao.getId();		
		this.descricao    = tipoComissao.getDescricao();
		
//		add(linkTo(ComissaoController.class).withRel("comissoes"));
    	    	
	}
	

	
}
