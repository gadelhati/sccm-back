package br.com.fattoria.sccm.api;

import java.util.Calendar;

import br.com.fattoria.sccm.persistence.model.MidiaParticular;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class MidiaParticularApi {
	
	private Long id;
	
	private String comissaoProjeto;
	
	private Long numeroAutorizacao;
	
	private Long numeroRA;
	
	private String numeroOficio;
	
	private Long codigoC;
	
	private Calendar data;
	
	private Long idPlataforma;
	
	private Long idInstituicao;
	
	private String observacoes;
	
	public MidiaParticular toEntity() {
		return new MidiaParticular(id, null, null, comissaoProjeto, numeroAutorizacao, numeroRA, numeroOficio, codigoC, data, observacoes);
	}

}
