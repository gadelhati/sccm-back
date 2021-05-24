package br.com.fattoria.sccm.api;

import br.com.fattoria.sccm.persistence.model.EstacaoMeteorologica;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class EstacaoMeteorologicaApi {

	private Long id;

	private String nome;

	private String latitude;

	private String longitude;
	
	private boolean ativo;

	public EstacaoMeteorologica toEntity() {
		return new EstacaoMeteorologica(id, nome, latitude, longitude, ativo);
	}

}
