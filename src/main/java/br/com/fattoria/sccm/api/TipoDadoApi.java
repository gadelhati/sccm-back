package br.com.fattoria.sccm.api;

import br.com.fattoria.sccm.persistence.model.TipoDado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TipoDadoApi {
	
	private Long id;

	private String descricao;

	private boolean ativo;
	
	private Long idUnidadeMedida;
	
	private UnidadeMedidaApi unidadeMedidaApi;
	
	public TipoDado toEntity() {
		return new TipoDado(id, descricao, ativo, null);
	}

}
