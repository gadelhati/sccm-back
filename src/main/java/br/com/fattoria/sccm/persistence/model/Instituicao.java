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
@Table(name = "instituicao")
@SequenceGenerator(name="instituicao_generator", sequenceName="instituicao_seq", allocationSize = 1)
public class Instituicao {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="instituicao_generator")
	private Long id;

	private String nome;

}
