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
@Table(name = "destino")
@SequenceGenerator(name="assinatura_pc_generator", sequenceName="assinatura_pc_seq", allocationSize = 1)
public class AssinaturaPC implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="assinatura_pc_generator")
	private Long id;
	
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "fk_destino")
	private Destino destino;
	
	private Boolean ativo;
	
	private String patente;
	
	private String cargo;
	
}
