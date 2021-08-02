package br.com.fattoria.sccm.api;

import br.com.fattoria.sccm.persistence.model.Destino;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class DestinoApi {
	
	private Long id;
	
	private String destino;
	
	private boolean ativo;
	
	public Destino toEntity() {
		return new Destino(id, destino, ativo);
	}

}
