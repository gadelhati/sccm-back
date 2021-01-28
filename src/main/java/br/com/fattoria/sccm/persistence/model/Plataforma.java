package br.com.fattoria.sccm.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "plataforma")
@SequenceGenerator(name="plataforma_generator", sequenceName="plataforma_seq", allocationSize = 1)
public class Plataforma {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="plataforma_generator")
	private Long id;
	
	private String nome;

	@Column(name = "INDICATIVO_VISUAL")
	private String indicativoVisual;
	
	private String comandante;
	
	@Column(name = "BANDEIRA_EMBARCACAO")
	private String bandeiraEmbarcacao;
	
	@ManyToOne
	@JoinColumn(name = "FK_TIPO_PLATAFORMA")
	private TipoPlataforma tipoPlataforma;
	
	private boolean ativo;

	
}
