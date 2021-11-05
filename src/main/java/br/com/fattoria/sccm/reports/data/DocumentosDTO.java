package br.com.fattoria.sccm.reports.data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.fattoria.sccm.persistence.model.Documento;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class DocumentosDTO {
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private String anexo;
	
	private String tipoAnexo;
	
	private String destino;
	
	private String dataRecebimento;
	
	public DocumentosDTO(Documento documento) {
		this.anexo = documento.getAnexo();
		this.tipoAnexo = documento.getTipoAnexo().getDescricao();
		this.destino = documento.getDestino().getDestino();
		this.dataRecebimento = documento.getDataRecebimento() != null ? sdf.format(documento.getDataRecebimento().getTime()) : null;		
	}
	
	public List<DocumentosDTO> getListToListDTO(List<Documento> lista) {
		
		List<DocumentosDTO> list = new ArrayList<DocumentosDTO>();
		
		for (Documento object : lista) {
			DocumentosDTO dto = new DocumentosDTO(object);
			list.add(dto);
		}
		
		return list;
	}
	

}
