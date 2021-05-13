package br.com.fattoria.sccm.persistence.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@Entity
@Table(name = "ship_synop")
@SequenceGenerator(name="ship_synop_generator", sequenceName="ship_synop_seq", allocationSize = 1)
public class ShipSynop implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ship_synop_generator")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "fk_plataforma")
	private Plataforma plataforma;
	
	@ManyToOne
	@JoinColumn(name = "fk_comissao")
	private Comissao comissao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "periodo_inicial")
	private Calendar periodoInicial;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "periodo_final")
	private Calendar periodoFinal;
	
	@ManyToOne
	@JoinColumn(name = "fk_pc")	
	private PesquisaCientifica pesquisaCientifica;
	
	@Column(name = "protocolo")
	private String protocolo;
	
	@Column(name = "numero_modelos")
	private Integer numeroModelos;
	
	@Column(name = "dados")
	private String dados;
	
	@Column(name = "numero_informacoes")
	private Integer numeroInformacoes;
	
	@Column(name = "corrigido")
	private Boolean corrigido;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_entrada")
	private Calendar dataEntrada;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_saida")
	private Calendar dataSaida;
	
	@Column(name = "observacoes")
	private String observacoes;
	
	@OneToOne
	@JoinColumn(name = "fk_situacoes")
	private Situacao situacao;
	
	@ManyToOne
	@JoinColumn(name = "fk_estacao_meteorologica")
	private EstacaoMeteorologica estacaoMeteorologica; 
}
