package br.com.fattoria.sccm.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
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
@Table(name = "palavraChave")
@SequenceGenerator(name="palavra_chave_generator", sequenceName="palavra_chave_seq", allocationSize = 1)
public class PalavraChave implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="palavra_chave_generator")
	private Long id;
	
	@Column(name = "palavra_chave")
	private String palavraChave;
	
	@ManyToOne
	@JoinColumn(name = "fk_pesquisa_cientifica")
	private PesquisaCientifica pesquisaCientifica;
	
}
