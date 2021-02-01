package br.com.fattoria.sccm.api;

import br.com.fattoria.sccm.persistence.model.AreaConhecimento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AreaConhecimentoApi {

	private Long id;
	
	private String nome;

	private String descricao;

	private boolean ativo;
	
	public AreaConhecimento toEntity() {
		return new AreaConhecimento(id, nome, descricao, ativo);
	}
	
}
