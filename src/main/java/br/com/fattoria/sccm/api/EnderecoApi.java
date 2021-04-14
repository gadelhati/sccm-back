package br.com.fattoria.sccm.api;

import br.com.fattoria.sccm.persistence.model.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
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
