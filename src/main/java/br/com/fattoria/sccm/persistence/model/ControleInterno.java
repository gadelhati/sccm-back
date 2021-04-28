package br.com.fattoria.sccm.persistence.model;

import java.io.Serializable;
import java.util.Calendar;

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
@Table(name = "controle_interno")
@SequenceGenerator(name="controle_interno_generator", sequenceName="controle_interno_seq", allocationSize = 1)
public class ControleInterno implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="controle_interno_generator")
	private Long id;
	
	@Column(name = "numero_oficio")
	private String numeroOficio;
	
	@ManyToOne
	@JoinColumn(name = "fk_instituicao")
	private Instituicao instituicao;
	
	@Column(name = "arquivo_tecnico")
	private String arquivoTecnico;
	
	@Column(name = "forma_envio")
	private String formaEnvio;
	
	@Column(name = "numero_autorizacao")
	private String numeroAutorizacao;
	
	@ManyToOne
	@JoinColumn(name = "fk_pc")
	private PesquisaCientifica pesquisaCientifica;
	
	@Column(name = "data_oficio")
	private Calendar dataOficio;
	
	@Column(name = "recibo")
	private String recibo;

}
