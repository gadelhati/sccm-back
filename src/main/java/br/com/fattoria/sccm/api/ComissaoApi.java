package br.com.fattoria.sccm.api;

import java.util.Calendar;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.fattoria.sccm.persistence.model.Comissao;
import br.com.fattoria.sccm.persistence.model.TipoComissao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ComissaoApi {
	
	private Long id;
	
	@NotBlank
	@NotBlank
	@NotEmpty
	private String nomeComissao;
	
	private Calendar dataInicio;
	
	private Calendar dataFim;
	
	private String resumo;
	
	private String palavrasChaves;
	
	@NotNull
	private Long idTipoComissao;
	
	public Comissao toEntity() {
		return new Comissao(id, idTipoComissao != null ? new TipoComissao(idTipoComissao) : null, nomeComissao, dataInicio, dataFim, resumo, palavrasChaves);
	}

}
