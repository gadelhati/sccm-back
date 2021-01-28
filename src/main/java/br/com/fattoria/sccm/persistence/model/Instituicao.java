package br.com.fattoria.sccm.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "instituicao")
@SequenceGenerator(name="instituicao_generator", sequenceName="instituicao_seq", allocationSize = 1)
public class Instituicao {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="instituicao_generator")
	private Long id;

	@Column(name = "nome")
	private String nome;
	
	@Column(name = "sigla")
	private String sigla;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_instituicao")
	private TipoInstituicao tipoInstituicao;
	
	@Column(name = "numero_inscricao")
	private Long numeroInscricao;
	
	@Column(name = "numero_controle")
	private Long numeroControle;
	
	@ManyToOne
	@JoinColumn(name = "fk_pais")
	private Pais pais;

	

	
}
