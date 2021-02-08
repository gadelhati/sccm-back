package br.com.fattoria.sccm.persistence.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comissao")
@SequenceGenerator(name="comissao_generator", sequenceName="comissao_seq", allocationSize = 1)
public class Comissao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="plataforma_generator")
	private Long id;
	
	@Column(name = "numero_pc")
	private String numeroPC;
	
	@Column(name = "nome_comissao")
	private String nomeComissao;
	
	@Column(name = "nome_cruzeiro")
	private String nomeCruzeiro;
	
	@Column(name = "pernada")
	private Integer pernada;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_entrada")
	private Calendar dataEntrada;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_oficial")
	private Calendar dataOficial;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_inicio")
	private Calendar dataInicio;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_fim")
	private Calendar dataFim;
	
	@Column(name = "observacoes")
	private String observacoes;
	
	@Column(name = "imagem")
	private String imagem;

}
