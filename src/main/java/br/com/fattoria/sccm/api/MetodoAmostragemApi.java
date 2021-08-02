package br.com.fattoria.sccm.api;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.fattoria.sccm.persistence.model.MetodoAmostragem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class MetodoAmostragemApi {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="metodo_amostragem_generator")
	private Long id;
	
	private String descricao;
	
	private boolean ativo;
	
	public MetodoAmostragem toEntity() {
		return new MetodoAmostragem(id, descricao, ativo);
	}
	
}
