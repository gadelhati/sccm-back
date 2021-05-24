package br.com.fattoria.sccm.reports.data;

import java.util.ArrayList;
import java.util.List;

import br.com.fattoria.sccm.persistence.model.Equipamento;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class EquipamentoDTO {

	private String nome;
	
	public EquipamentoDTO(Equipamento equipamento) {
		this.nome = equipamento.getNome();		
	}
	
	public List<EquipamentoDTO> getListToListDTO(List<Equipamento> lista) {
		
		List<EquipamentoDTO> list = new ArrayList<EquipamentoDTO>();
		
		for (Equipamento object : lista) {
			EquipamentoDTO dto = new EquipamentoDTO(object);
			list.add(dto);
		}
		
		return list;
	}
	
}
