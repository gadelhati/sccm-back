package br.com.fattoria.sccm.api;

import br.com.fattoria.sccm.persistence.model.TipoEndereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TipoEnderecoApi {

	private Long id;
	
	private String descricao;
	
	public TipoEndereco toEntity() {
		return new TipoEndereco(id, descricao);
	}
	
}
