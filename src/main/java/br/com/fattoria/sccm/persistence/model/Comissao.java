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
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="comissao_generator")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "fk_tipo_comissao")
	private TipoComissao tipoComissao;
		
	@Column(name = "nome_comissao")
	private String nomeComissao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_inicio")
	private Calendar dataInicio;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_fim")
	private Calendar dataFim;
	
	@Column(name = "resumo")
	private String resumo;
	
	@Column(name = "palavras_chaves")
	private String palavrasChaves;

}
