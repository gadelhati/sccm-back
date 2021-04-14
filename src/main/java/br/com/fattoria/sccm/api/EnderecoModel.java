package br.com.fattoria.sccm.api;

import org.springframework.hateoas.RepresentationModel;

import br.com.fattoria.sccm.persistence.model.Endereco;
import lombok.Getter;

@Getter
public class EnderecoModel extends RepresentationModel<EnderecoModel>{
	
	private Long id;
	
	private String logradouro;
	
	private String numero;
	
	private String complemento;
	
	private String cidade;
	
	private String estado;
	
	private String bairro;
	
	private String cep;
	
    public EnderecoModel(Endereco endereco) {
    	this.id = endereco.getId();
    	this.logradouro = endereco.getLogradouro();
    	this.numero = endereco.getNumero();
    	this.complemento = endereco.getComplemento();
    	this.cidade = endereco.getCidade();
    	this.cep = endereco.getCep();
//    	add(linkTo(EnderecoController.class).withRel("paises"));
//        add(linkTo(methodOn(EnderecoController.class).getById(pais.getId())).withSelfRel());
	}
	
	public Endereco toEntity() {
		return new Endereco(id, null, logradouro, numero, complemento, cidade, estado, bairro, cep);
	}

}
