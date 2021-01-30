package br.com.fattoria.sccm.api;

import br.com.fattoria.sccm.persistence.model.Pais;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PaisApi {
	
	private Long id;
	
	private String nome;

	private String nomeIngles;
	
	public Pais toEntity() {
		return new Pais(id, nome, nomeIngles);
	}

}
