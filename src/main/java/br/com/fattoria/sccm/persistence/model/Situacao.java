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
@Table(name = "situacao")
@SequenceGenerator(name="situacao_generator", sequenceName="situacao_sec", allocationSize = 1)
public class Situacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="situacao_generator")
	private Long id;
	
	private String descricao;
	
	@Column(name = "para_pesquisa_cientifica")
	private boolean paraPesquisaCientifica;
	
	@Column(name = "para_ship_synop")
	private boolean paraShipSynop;
	
	@Column(name = "para_midias_diversas")
	private boolean paraMidiasDiversas;
	
	@Column(name = "para_midias_particulares")
	private boolean paraMidiasParticulares;
	
	private boolean ativo;
	
}
