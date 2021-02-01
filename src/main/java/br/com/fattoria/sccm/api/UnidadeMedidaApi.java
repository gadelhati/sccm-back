package br.com.fattoria.sccm.api;

import br.com.fattoria.sccm.persistence.model.UnidadeMedida;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UnidadeMedidaApi {

	private Long id;
	
	private String descricao;
	
	public UnidadeMedida toEntity() {
		return new UnidadeMedida(id, descricao);
	}
	
}
