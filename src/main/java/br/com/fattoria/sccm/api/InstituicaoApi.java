package br.com.fattoria.sccm.api;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.util.ObjectUtils;

import br.com.fattoria.sccm.persistence.model.Endereco;
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

	@NotEmpty @NotNull
	private String nome;
	
	@NotEmpty @NotNull
	private String sigla;
	
	private TipoInstituicao tipoInstituicao;
	
	private Long numeroInscricao;
	
	private Long numeroControle;
	
	private Long idPais;
	
	private boolean ativo;
	
	private String email;
	
	private String site;
	
	private String logradouro;
	
	private String numero;
	
	private String complemento;
	
	private String cidade;
	
	private String cep;
	
	//private EnderecoApi endereco;
	
	public Instituicao toEntity() {
		Endereco endereco = !ObjectUtils.isEmpty(logradouro) && !ObjectUtils.isEmpty(cidade) && !ObjectUtils.isEmpty(cep) && !ObjectUtils.isEmpty(numero) ? 
				new Endereco(id, null, logradouro, numero, complemento, cidade, cep) : null;
		return new Instituicao(id, nome, sigla, tipoInstituicao, numeroInscricao, numeroControle, null, ativo, email, site, endereco);//endereco != null ? endereco.toEntity() : null
	}

}
