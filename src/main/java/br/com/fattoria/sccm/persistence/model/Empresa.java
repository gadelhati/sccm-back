package br.com.fattoria.sccm.persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Audited
@Entity
@Table(name = "empresa")
@SequenceGenerator(name="empresa_generator", sequenceName="empresa_seq", allocationSize = 1)
public class Empresa {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="empresa_generator")
	private Long id;

	@NotNull
	@NotBlank
	@NotEmpty
	private String nome;

}
