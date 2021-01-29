package br.com.fattoria.sccm.api;

import br.com.fattoria.sccm.persistence.model.Plataforma;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PlataformaApi {
	
	private Long id;
	
	private String nome;

	private String identificadorVisual;
	
	private Long idBandeira;
	
	private Long idTipoPlataforma;
	
	private boolean ativo;

	public Plataforma toPlataformaEntity() {
		return new Plataforma(id, nome, identificadorVisual, null, null, ativo);
	}
	

}
