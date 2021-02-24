package br.com.fattoria.sccm.reports.data;

import br.com.fattoria.sccm.persistence.model.PesquisaCientifica;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class DataPesquisaCientifica {
	
	private String numeroPC;
	
	private String sigilo;
	
	private String comissao;
	
	private String instituicao;
	
	private String plataforma;
	
	DataPesquisaCientifica(PesquisaCientifica pesquisaCientifica) {
		if(pesquisaCientifica != null) {
			this.numeroPC = pesquisaCientifica.getNumeroPC();
			this.sigilo = pesquisaCientifica.getSigilo() != null ? pesquisaCientifica.getSigilo().getDescricao() : "";
			this.comissao = pesquisaCientifica.getComissao() != null ? pesquisaCientifica.getComissao().getNomeComissao() : "";
			this.instituicao = pesquisaCientifica.getInstituicao() != null ? pesquisaCientifica.getInstituicao().getNome() : "";
			this.plataforma = pesquisaCientifica.getPlataforma() != null ? pesquisaCientifica.getPlataforma().getNome() : "";
		}
	}

}
