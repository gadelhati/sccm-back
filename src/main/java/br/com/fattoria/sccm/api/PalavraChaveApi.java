package br.com.fattoria.sccm.api;

import br.com.fattoria.sccm.persistence.model.PalavraChave;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PalavraChaveApi {
	
	private Long id;
	
	private String palavraChave;
	
	private Long idPesquisaCientifica;
	
	public PalavraChave toEntity() {
		return new PalavraChave(id, palavraChave, null);
	}
	
}
