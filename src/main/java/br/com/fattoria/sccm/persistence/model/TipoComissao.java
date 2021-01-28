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
@Table(name = "tipo_comissao")
@SequenceGenerator(name="tipo_comissao_generator", sequenceName="tipo_comissao_seq", allocationSize = 1)
public class TipoComissao {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tipo_comissao_generator")
	private Long id;

	private String descricao;

	private boolean ativo;

	
}
