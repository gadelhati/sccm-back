package br.com.fattoria.sccm.api;

import br.com.fattoria.sccm.persistence.model.AreaTecnica;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AreaTecnicaApi {
	
	private Long id;
	
	private String descricao;
	
	public AreaTecnica toEntity() {
		return new AreaTecnica(id, descricao);
	}

}
