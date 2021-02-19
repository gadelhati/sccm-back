package br.com.fattoria.sccm.api;

import java.util.Calendar;
import java.util.List;

import javax.validation.constraints.NotNull;

import br.com.fattoria.sccm.persistence.model.PesquisaCientifica;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PesquisaCientificaApi {
	
	private Long id;	

	@NotNull
	private Long idSigilo;

	@NotNull
	private Long idInstituicao;

	@NotNull
	private Long idPlataforma;

	private Calendar dataCadastro;

	private String comandante;
	
	private String chefeCientifico;
		
	private String cartaNautica;
	
	private String limiteNorteLatitude;
	
	private String limiteSulLatitude;
	
	private String limiteLesteLongitude;
	
	private String limiteOesteLongitude;
	
	private String numeroPC;
	
	private ComissaoApi comissao;
	
	private List<Long> idsEquipamentos;
	
	private List<Long> idsAreasConhecimento;
	
	public PesquisaCientifica toEntity() {
		return new PesquisaCientifica(id, null, comissao != null ? comissao.toEntity() : null, null, null, dataCadastro, comandante, chefeCientifico, cartaNautica, limiteNorteLatitude, limiteSulLatitude, limiteLesteLongitude, limiteOesteLongitude, numeroPC, null, null, null);
	}
	
}
