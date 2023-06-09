package br.com.fattoria.sccm.api;

import java.util.List;

import br.com.fattoria.sccm.persistence.model.Equipamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class EquipamentoApi {

	private Long id;
	
	private String nome;

	private boolean ativo;

	private String sigla;

	private String codigo;

	private String descricao;
	
	private Long idAreaTecnica;
	
	private Long idMetodoAmostragem;

	private List<Long> idsAreaConhecimento;
	
	private List<Long> idsTipoDado;
	
	public Equipamento toEntity() {
		return new Equipamento(id, nome, ativo, sigla, codigo, descricao, null, null);
	}
}
