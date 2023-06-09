package br.com.fattoria.sccm.api;

import java.util.Calendar;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.fattoria.sccm.persistence.model.Documento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class DocumentosApi {

	private Long id;
	
	@NotEmpty @NotNull
	private String anexo;
	
	private Calendar dataRecebimento;
	
	private Long idTipoAnexo;
	
	private Long idDestino;
	
	private String observacoes;
	
	@NotNull
	private Long idControleInterno;
	
	public Documento toEntity() {
		return new Documento(id, anexo, dataRecebimento, null, null, null, observacoes);
	}
}
