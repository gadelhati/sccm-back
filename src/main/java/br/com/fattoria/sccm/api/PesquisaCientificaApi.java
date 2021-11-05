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
	
	private String coordenadorCientifico;
		
	private String cartaNautica;
	
	@Pattern(regexp = RegexLatLong.LATITUDE_PATTERN, message = "Latitude Norte inválida")
	private String limiteNorteLatitude;
	
	@Pattern(regexp = RegexLatLong.LATITUDE_PATTERN, message = "Latitude Sul inválida")
	private String limiteSulLatitude;
	
	@Pattern(regexp = RegexLatLong.LONGITUDE_PATTERN, message = "Longitude Leste inválida")
	private String limiteLesteLongitude;
	
	@Pattern(regexp = RegexLatLong.LONGITUDE_PATTERN, message = "Longitude Oeste inválida")
	private String limiteOesteLongitude;
	
	private String numeroPC;
	
	private String numeroIEPortariaAutorizacaoPesquisa;
	
	private String indiceH;
	
	@NotNull
	private Long idSituacao;
	
	private ComissaoApi comissao;
	
	private List<Long> idsEquipamentos;
	
	private List<Long> idsAreasConhecimento;
	
	private List<Long> idsCoParticipantes;
	
	private String usuarioCadastro;
	
	public PesquisaCientifica toEntity() {		
		return new PesquisaCientifica(id, null, comissao != null ? comissao.toEntity() : null, null, null, dataCadastro, comandante, coordenadorCientifico, 
				cartaNautica, limiteNorteLatitude, limiteSulLatitude, limiteLesteLongitude, limiteOesteLongitude, numeroPC, 
				numeroIEPortariaAutorizacaoPesquisa, indiceH, null, null, null, usuarioCadastro, null);
	}
	
}
