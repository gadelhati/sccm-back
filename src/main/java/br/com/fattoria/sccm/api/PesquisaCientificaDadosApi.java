package br.com.fattoria.sccm.api;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PesquisaCientificaDadosApi {
	
	@NotNull
	private Long idPesquisaCientifica;	
	
	private List<Long> idsDadosEquipamentos;

}
