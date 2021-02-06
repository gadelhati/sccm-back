package br.com.fattoria.sccm.api;

import br.com.fattoria.sccm.persistence.model.Endereco;

public class EnderecoApi {
	
	private Long id;
	
	private String logradouro;
	
	private String numero;
	
	private String complemento;
	
	private String cidade;
	
	private String cep;
	
	public Endereco toEntity() {
		return new Endereco(id, null, logradouro, numero, complemento, cidade, cep);
	}

}
