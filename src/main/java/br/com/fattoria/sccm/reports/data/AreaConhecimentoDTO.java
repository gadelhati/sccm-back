package br.com.fattoria.sccm.reports.data;

import java.util.ArrayList;
import java.util.List;

import br.com.fattoria.sccm.persistence.model.AreaConhecimento;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class AreaConhecimentoDTO {

	private String nome;	

	public AreaConhecimentoDTO(AreaConhecimento areaConhecimento) {
		this.nome = areaConhecimento.getNome();
	}
	
	public List<AreaConhecimentoDTO> getListToListDTO(List<AreaConhecimento> lista) {
		
		List<AreaConhecimentoDTO> list = new ArrayList<AreaConhecimentoDTO>();
		
		for (AreaConhecimento areaConhecimento : lista) {
			AreaConhecimentoDTO dto = new AreaConhecimentoDTO(areaConhecimento);
			list.add(dto);
		}
		
		return list;
	}
}
