package br.com.fattoria.sccm.api;

import java.util.Calendar;

import br.com.fattoria.sccm.persistence.model.Documentos;
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
	
	private String anexo;
	
	private Calendar dataRecebimento;
	
	private Long idTipoAnexo;
	
	private Long idDestino;
	
	public Documentos toEntity() {
		return new Documentos(id, anexo, dataRecebimento, null, null);
	}
}
