package br.com.fattoria.sccm.persistence.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import br.com.fattoria.sccm.persistence.listener.PequisaCientificaListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@Entity
@Table(name = "pesquisa_cientifica")
@EntityListeners(PequisaCientificaListener.class)
@SequenceGenerator(name="pesquisa_cientifica_generator", sequenceName="pesquisa_cientifica_seq", allocationSize = 1)
public class PesquisaCientifica implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pesquisa_cientifica_generator")
	private Long id;	

	@ManyToOne
	@JoinColumn(name = "fk_sigilo")
	private Sigilo sigilo;
	
	@OneToOne(cascade = CascadeType.ALL)
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
	
	@Column(name = "coordenador_cientifico")
	private String coordenadorCientifico;
	
	@Column(name = "carta_nautica")
	private String cartaNautica;
	
	@Column(name = "limite_norte_latitude")
	private String limiteNorteLatitude;
	
	@Column(name = "limite_sul_latitude")
	private String limiteSulLatitude;
	
	@Column(name = "limite_leste_longitude")
	private String limiteLesteLongitude;
	
	@Column(name = "limite_oeste_longitude")
	private String limiteOesteLongitude;
	
	@Column(name = "numero_pc")
	private String numeroPC;
	
	@Column(name = "numero_ie_portaria_autorizacao_pesquisa")
	private String numeroIEPortariaAutorizacaoPesquisa;
	
	@Column(name = "indice_h")
	private String indiceH;
	
	@OneToOne
	@JoinColumn(name = "fk_situacao")
	private Situacao situacao;
	
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
	
	@Column(name = "usuario_cadastro")
	private String usuarioCadastro;

	@Transient
	private List<TipoDado> listaTiposDados;
	
}
