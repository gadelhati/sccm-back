package br.com.fattoria.sccm.persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "area_conhecimento")
@SequenceGenerator(name="area_conhecimento_generator", sequenceName="area_conhecimento_seq", allocationSize = 1)
public class AreaConhecimento {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="area_conhecimento_generator")
	private Long id;
	
	private String nome;

	private String descricao;

	private boolean ativo;

	
}
