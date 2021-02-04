package br.com.fattoria.sccm.persistence.model;

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
@Table(name = "tipo_dados")
@SequenceGenerator(name="tipo_dado_generator", sequenceName="tipo_dado_seq", allocationSize = 1)
public class TipoDado {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tipo_dado_generator")
	private Long id;

	private String descricao;

	private boolean ativo;
	
	@ManyToOne
	@JoinColumn(name = "fk_unidade_medida")
	private UnidadeMedida unidadeMedida;
	
}
