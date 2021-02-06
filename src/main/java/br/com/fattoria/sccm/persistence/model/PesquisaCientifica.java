package br.com.fattoria.sccm.persistence.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pesquisaCientifica")
@SequenceGenerator(name="pesquisa_cientifica_generator", sequenceName="pesquisa_cientifica_seq", allocationSize = 1)
public class PesquisaCientifica implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pesquisa_cientifica_generator")
	private Long id;
	
	@OneToMany
	@JoinColumn(name = "fk_sigilo")
	private Sigilo sigilo;
	
	@OneToMany
	@JoinColumn(name = "fk_comissao")
	private Comissao comissao;
	
	@OneToMany
	@JoinColumn(name = "fk_instituicao")
	private Instituicao instituicao;
	
	@OneToMany
	@JoinColumn(name = "fk_plataforma")
	private Plataforma plataforma;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro")
	private Calendar dataCadastro;
	
	@Column(name = "comandante")
	private String comandante;
	
	
	private List<PesquisaCientificaAreaConhecimento> listaAreaConhecimento;
	
	private List<PesquisaCientificaDados> listaDados;
	
	private List<PesquisaCientificaEquipamento> listaEquipamentos;
	
	private List<PesquisaCientificaDocumento> listaDocumentos;
	
	
}
