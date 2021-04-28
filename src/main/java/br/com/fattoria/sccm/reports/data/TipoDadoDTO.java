package br.com.fattoria.sccm.reports.data;

import java.util.ArrayList;
import java.util.List;

import br.com.fattoria.sccm.persistence.model.TipoDado;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class TipoDadoDTO {

	private String descricao;
	
	public TipoDadoDTO(TipoDado tipoDado) {
		this.descricao = tipoDado.getDescricao();
	}
	
	public List<TipoDadoDTO> getListToListDTO(List<TipoDado> lista) {
		
		List<TipoDadoDTO> list = new ArrayList<TipoDadoDTO>();
		
		for (TipoDado object : lista) {
			TipoDadoDTO dto = new TipoDadoDTO(object);
			list.add(dto);
		}
		
		return list;
	}
	
}
