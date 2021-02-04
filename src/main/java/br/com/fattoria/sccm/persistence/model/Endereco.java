package br.com.fattoria.sccm.persistence.model;

import java.io.Serializable;

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
@Table(name = "endereco")
@SequenceGenerator(name = "endereco_generator", sequenceName = "endereco_seq", allocationSize = 1)
public class Endereco implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="endereco_generator")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "fk_tipo_endereco")
	private TipoEndereco tipoEndereco;
	
	private String logradouro;
	
	private String numero;
	
	private String complemento;
	
	private String cidade;
	
	private String cep;

}
