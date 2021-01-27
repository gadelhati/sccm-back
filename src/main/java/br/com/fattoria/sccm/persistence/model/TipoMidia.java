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
@Table(name = "tipo_midia")
@SequenceGenerator(name="tipo_midia_generator", sequenceName="tipo_midia_seq", allocationSize = 1)
public class TipoMidia {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tipo_midia_generator")
	private Long id;

	private String descricao;

	private boolean ativo;
	
}
