package br.com.fattoria.sccm.api;

import java.util.Calendar;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.com.fattoria.sccm.persistence.model.PesquisaCientifica;
import br.com.fattoria.sccm.persistence.validation.RegexLatLong;
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
	
	@Pattern(regexp = RegexLatLong.LATITUDE_PATTERN, message = "Latitude Norte inválida")
	private String limiteNorteLatitude;
	
	@Pattern(regexp = RegexLatLong.LATITUDE_PATTERN, message = "Latitude Sul inválida")
	private String limiteSulLatitude;
	
	@Pattern(regexp = RegexLatLong.LONGITUDE_PATTERN, message = "Latitude Leste inválida")
	private String limiteLesteLongitude;
	
	@Pattern(regexp = RegexLatLong.LONGITUDE_PATTERN, message = "Latitude Oeste inválida")
	private String limiteOesteLongitude;
	
	private String numeroPC;
	
	private ComissaoApi comissao;
	
	private List<Long> idsEquipamentos;
	
	private List<Long> idsAreasConhecimento;
	
	private List<Long> idsCoParticipantes;
	
	public PesquisaCientifica toEntity() {
		return new PesquisaCientifica(id, null, comissao != null ? comissao.toEntity() : null, null, null, dataCadastro, comandante, chefeCientifico, cartaNautica, limiteNorteLatitude, limiteSulLatitude, limiteLesteLongitude, limiteOesteLongitude, numeroPC, null, null, null);
	}
	
}
