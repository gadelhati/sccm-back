package br.com.fattoria.sccm.persistence.model;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

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
@Table(name = "midia_particular")
@SequenceGenerator(name="midia_particular_generator", sequenceName="midia_particular_generator_seq", allocationSize = 1)
public class MidiaParticular {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="midia_particular_generator")
	private Long id;

	@ManyToOne
	@JoinColumn(name="fk_instituicao")
	private Instituicao instituicao;
	
	@ManyToOne
	@JoinColumn(name="fk_plataforma")
	private Plataforma plataforma;
	
	@Column(name="comissao_projeto")
	private String comissaoProjeto;
	
	@Column(name="numero_autorizacao")
	private Long numeroAutorizacao;
	
	@Column(name="numero_ra")
	private Long numeroRA;
	
	@Temporal(TemporalType.DATE)
	private Calendar data;

	private String observacoes;

}
