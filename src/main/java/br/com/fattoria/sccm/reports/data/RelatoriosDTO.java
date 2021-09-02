package br.com.fattoria.sccm.reports.data;

import java.util.Collection;

import br.com.fattoria.sccm.dto.ListagemSynopDTO;
import br.com.fattoria.sccm.dto.QuantitativoDTO;
import br.com.fattoria.sccm.persistence.model.PesquisaCientifica;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode	
@NoArgsConstructor
@AllArgsConstructor
public class RelatoriosDTO {

//	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private String periodoInicial;
	
	private String periodoFinal;
	
	private int qtdPCRecebidosNovo;
	
	private Collection<QuantitativoDTO> tiposComissao;
	
	private Collection<QuantitativoDTO> dadosEquipamentosRecebidos;
	
	private Collection<QuantitativoDTO> porSituacao;
	
	private Collection<PesquisaCientifica> pesquisaRecebidasPendentes;
	
	private Collection<QuantitativoDTO> shipModeloPorSituacoes;
	
	private Collection<QuantitativoDTO> shipInformacoesPorSituacoes;
	
	private Collection<QuantitativoDTO> synopModeloPorSituacoes;
	
	private Collection<QuantitativoDTO> synopInformacoesPorSituacoes;
	
	private Collection<ListagemSynopDTO> dadosEstacoesMeteorologicas;
	
	private Collection<QuantitativoDTO> midiasParticularesPorSituacoes;
	
	private Collection<QuantitativoDTO> midiasDiversasPorSituacoes;
	
}
