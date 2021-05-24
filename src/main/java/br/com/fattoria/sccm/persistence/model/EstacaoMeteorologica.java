package br.com.fattoria.sccm.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
@Table(name = "estacao_meteriologica")
@SequenceGenerator(name="estacao_meteriologica_generator", sequenceName="estacao_meteriologica_seq", allocationSize = 1)
public class EstacaoMeteorologica implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="estacao_meteriologica_generator")
	private Long id;
	
	private String nome;
	
	@Column(name = "latitude")
	private String latitude;
	
	@Column(name = "longitude")
	private String longitude;
	
	private boolean ativo;
	
}
