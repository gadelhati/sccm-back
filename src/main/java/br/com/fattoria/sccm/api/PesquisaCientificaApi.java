package br.com.fattoria.sccm.api;

import java.util.Calendar;

import javax.persistence.Column;

import br.com.fattoria.sccm.persistence.model.Comissao;
import br.com.fattoria.sccm.persistence.model.Instituicao;
import br.com.fattoria.sccm.persistence.model.PesquisaCientifica;
import br.com.fattoria.sccm.persistence.model.Plataforma;
import br.com.fattoria.sccm.persistence.model.Sigilo;
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

	private Long idSigilo;

	private Long idComissao;

	private Long idInstituicao;

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
	
	public PesquisaCientifica toEntity() {
		return new PesquisaCientifica(id, null, null, null, null, dataCadastro, comandante, chefeCientifico, cartaNautica, limiteNorteLatitude, limiteSulLatitude, limiteLesteLongitude, limiteOesteLongitude, numeroPC, null, null, null);
	}
	
}
