package br.com.fattoria.sccm.api;

import br.com.fattoria.sccm.persistence.model.Instituicao;
import br.com.fattoria.sccm.persistence.model.TipoInstituicao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class InstituicaoApi {
	
	private Long id;

	private String nome;
	
	private String sigla;
	
	private TipoInstituicao tipoInstituicao;
	
	private Long numeroInscricao;
	
	private Long numeroControle;
	
	private Long idPais;
	
	private boolean ativo;
	
	public Instituicao toEntity() {
		return new Instituicao(id, nome, sigla, tipoInstituicao, numeroInscricao, numeroControle, null, ativo);
	}

}
