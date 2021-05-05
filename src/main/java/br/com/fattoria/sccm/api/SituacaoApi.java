package br.com.fattoria.sccm.api;

import br.com.fattoria.sccm.persistence.model.Situacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SituacaoApi {

	private Long id;

	private String descricao;

	private boolean paraPesquisaCientifica;

	private boolean paraShipSynop;
	
	private boolean ativo;

	public Situacao toEntity() {
		return new Situacao(id, descricao, paraPesquisaCientifica, paraShipSynop, ativo);
	}

}
