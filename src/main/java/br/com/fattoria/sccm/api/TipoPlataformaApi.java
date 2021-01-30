package br.com.fattoria.sccm.api;

import br.com.fattoria.sccm.persistence.model.TipoPlataforma;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TipoPlataformaApi {
	
	private Long id;
	
	private String descricao;
	
	private boolean ativo;

	public TipoPlataforma toEntity() {
		return new TipoPlataforma(id, descricao, ativo);
	}
	

}
