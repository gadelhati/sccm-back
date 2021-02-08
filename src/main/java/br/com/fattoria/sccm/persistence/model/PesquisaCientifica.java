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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
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
@Table(name = "pesquisa_cientifica")
@SequenceGenerator(name="pesquisa_cientifica_generator", sequenceName="pesquisa_cientifica_seq", allocationSize = 1)
public class PesquisaCientifica implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pesquisa_cientifica_generator")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "fk_sigilo")
	private Sigilo sigilo;
	
	@OneToOne
	@JoinColumn(name = "fk_comissao")
	private Comissao comissao;
	
	@OneToOne
	@JoinColumn(name = "fk_instituicao")
	private Instituicao instituicao;
	
	@OneToOne
	@JoinColumn(name = "fk_plataforma")
	private Plataforma plataforma;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro")
	private Calendar dataCadastro;
	
	@Column(name = "comandante")
	private String comandante;
	
	@ManyToMany
    @JoinTable(name = "pesquisa_cientifica_area_conhecimento", 
    joinColumns = {@JoinColumn(name="fk_pesquisa_cientifica")}, 
    inverseJoinColumns = {@JoinColumn(name="fk_area_conhecimento")})
	private List<AreaConhecimento> listaAreaConhecimento;
	
	@ManyToMany
    @JoinTable(name = "pesquisa_cientifica_equipamento", 
    joinColumns = {@JoinColumn(name="fk_pesquisa_cientifica")}, 
    inverseJoinColumns = {@JoinColumn(name="fk_equipamento")})
	private List<Equipamento> listaEquipamentos;
	
	@ManyToMany
    @JoinTable(name = "pesquisa_cientifica_equipamento", 
    joinColumns = {@JoinColumn(name="fk_pesquisa_cientifica")}, 
    inverseJoinColumns = {@JoinColumn(name="fk_equipamento")})
	private List<Documento> listaDocumentos;
	
	
}
