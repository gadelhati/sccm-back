package br.com.fattoria.sccm.reports.data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import br.com.fattoria.sccm.dto.ListagemSynopDTO;
import br.com.fattoria.sccm.dto.QuantitativoDTO;
import br.com.fattoria.sccm.persistence.model.AreaConhecimento;
import br.com.fattoria.sccm.persistence.model.Comissao;
import br.com.fattoria.sccm.persistence.model.Equipamento;
import br.com.fattoria.sccm.persistence.model.Instituicao;
import br.com.fattoria.sccm.persistence.model.PesquisaCientifica;
import br.com.fattoria.sccm.persistence.model.Plataforma;
import br.com.fattoria.sccm.persistence.model.Sigilo;
import br.com.fattoria.sccm.persistence.model.Situacao;
import br.com.fattoria.sccm.persistence.model.TipoDado;
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
	
	/*private Collection<QuantitativoDTO> tiposComissao;
	
	private Collection<QuantitativoDTO> dadosEquipamentosRecebidos;
	
	private Collection<QuantitativoDTO> porSituacao;
	
	private Collection<PesquisaCientifica> pesquisaRecebidasPendentes;
	
	private Collection<QuantitativoDTO> shipModeloPorSituacoes;
	
	private Collection<QuantitativoDTO> shipInformacoesPorSituacoes;
	
	private Collection<QuantitativoDTO> synopModeloPorSituacoes;
	
	private Collection<QuantitativoDTO> synopInformacoesPorSituacoes;
	
	private Collection<ListagemSynopDTO> dadosEstacoesMeteorologicas;
	
	private Collection<QuantitativoDTO> midiasParticularesPorSituacoes;
	
	private Collection<QuantitativoDTO> midiasDiversasPorSituacoes;*/
	
}
