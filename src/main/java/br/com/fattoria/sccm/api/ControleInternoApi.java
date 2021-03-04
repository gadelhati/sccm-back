package br.com.fattoria.sccm.api;

import br.com.fattoria.sccm.persistence.model.ControleInterno;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ControleInternoApi {

	private Long id;
	
	private String numeroOficio;
	
	private Long idInstituicao;
	
	private String arquivoTecnico;
	
	private String formaEnvio;
	
	private String numeroAutorizacao;
	
	private Long idPesquisaCientifica;
	
	public ControleInterno toEntity() {
		return new ControleInterno(id, numeroOficio, null, arquivoTecnico, formaEnvio, numeroAutorizacao, null);
	}
	
}
