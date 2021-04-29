package br.com.fattoria.sccm.reports.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class FichaPesquisaCientificaDTO {
	
	public FichaPesquisaCientificaDTO(long id, String nome_comissao, String descricao, String nome, String logradouro,
			String cidade, String estado, String cep, String email, String resumo, String palavras_chaves,
			String coordenador_cientifico, String carta_nautica, String data_inicio, String data_fim,
			String limite_norte_latitude, String limite_leste_longitude, String limite_sul_latitude,
			String limite_oeste_longitude, String nome_plataforma, String tipo_plataforma, String comandante,
			String indicativo_visual, String bandeira, String paisinstituicao, String telefoneinstituicao,
			String outrasinstituicoes, String recibo, String oficio, String fitoteca, String arquivo_tecnico,
			String forma_envio, String numero_autorizacao, String nome_assinatura1, String nome_assinatura2, String nome_assinatura3,
			String patente1, String patente2, String patente3, String cargo1, String cargo2, String cargo3, 
			String destino1, String destino2, String destino3) {
		this.id = new Long(id);
		this.nome_comissao = nome_comissao;
		this.descricao = descricao;
		this.nome = nome;
		this.logradouro = logradouro;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
		this.email = email;
		this.resumo = resumo;
		this.palavras_chaves = palavras_chaves;
		this.coordenador_cientifico = coordenador_cientifico;
		this.carta_nautica = carta_nautica;
		this.data_inicio = data_inicio;
		this.data_fim = data_fim;
		this.limite_norte_latitude = limite_norte_latitude;
		this.limite_leste_longitude = limite_leste_longitude;
		this.limite_sul_latitude = limite_sul_latitude;
		this.limite_oeste_longitude = limite_oeste_longitude;
		this.nome_plataforma = nome_plataforma;
		this.tipo_plataforma = tipo_plataforma;
		this.comandante = comandante;
		this.indicativo_visual = indicativo_visual;
		this.bandeira = bandeira;
		this.paisinstituicao = paisinstituicao;
		this.telefoneinstituicao = telefoneinstituicao;
		this.outrasinstituicoes = outrasinstituicoes;
		this.recibo = recibo;
		this.oficio = oficio;
		this.fitoteca = fitoteca;
		this.arquivo_tecnico = arquivo_tecnico;
		this.forma_envio = forma_envio;
		this.numero_autorizacao = numero_autorizacao;
		this.nome_assinatura1 = nome_assinatura1;
		this.nome_assinatura2 = nome_assinatura2;
		this.nome_assinatura3 = nome_assinatura3;		
		this.patente1 = patente1;
		this.patente2 = patente2;
		this.patente3 = patente3;		
		this.cargo1 = cargo1;
		this.cargo2 = cargo2;
		this.cargo3 = cargo3;		
		this.destino1 = destino1;
		this.destino2 = destino2;
		this.destino3 = destino3;
	}
	private Long id;	
	private String nome_comissao;	
	private String descricao;	 
	private String nome;	
	private String logradouro; 	
	private String cidade;	
	private String estado;		
	private String cep;	
	private String email; 	
	private String resumo; 	
	private String palavras_chaves;	
	private String coordenador_cientifico;	
	private String carta_nautica;	
	private String data_inicio;  	
	private String data_fim;	 	
	private String limite_norte_latitude;	
	private String limite_leste_longitude;	
	private String limite_sul_latitude;	
	private String limite_oeste_longitude; 
	private String nome_plataforma;	
	private String tipo_plataforma;	
	private String comandante;	
	private String indicativo_visual;	
	private String bandeira;	
	private String paisinstituicao; 	
	private String telefoneinstituicao;	
	private String outrasinstituicoes;		
	private String recibo;	
	private String oficio;	
	private String fitoteca;	
	private String arquivo_tecnico;	
	private String forma_envio;	
	private String numero_autorizacao; 
	private String nome_assinatura1;
	private String nome_assinatura2;
	private String nome_assinatura3;
	private String patente1;
	private String patente2;
	private String patente3;
	private String cargo1;
	private String cargo2;
	private String cargo3;
	private String destino1;
	private String destino2;
	private String destino3;
	
	private List<AreaConhecimentoDTO> listaAreaConhecimento;
	
	private List<TipoDadoDTO> listaVariaveis;
	
	private List<EquipamentoDTO> listaEquipamentos;
	
	private List<DocumentosDTO> listaDocumentos;
	
	
	

}
