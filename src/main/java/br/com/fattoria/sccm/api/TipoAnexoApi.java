package br.com.fattoria.sccm.api;

import br.com.fattoria.sccm.persistence.model.TipoAnexo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TipoAnexoApi {

	private Long id;
	
	private String descricao;
	
	public TipoAnexo toEntity() {
		return new TipoAnexo(id, descricao);
	}
	
}
