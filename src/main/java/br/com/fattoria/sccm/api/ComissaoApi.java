package br.com.fattoria.sccm.api;

import java.util.Calendar;

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
	
	private String nomeComissao;
	
	private String nomeCruzeiro;
	
	private Integer pernada;
	
	private Calendar dataEntrada;
	
	private Calendar dataOficial;
	
	private Calendar dataInicio;
	
	private Calendar dataFim;
	
	private String observacoes;
	
	private String imagem;
	
	private Long idTipoComissao;
	
	public Comissao toEntity() {
		return new Comissao(id, idTipoComissao != null ? new TipoComissao(idTipoComissao) : null, nomeComissao, nomeCruzeiro, pernada, dataEntrada, dataOficial, dataInicio, dataFim, observacoes, imagem);
	}

}
