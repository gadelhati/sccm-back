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
@Table(name = "plataforma")
@SequenceGenerator(name="plataforma_generator", sequenceName="plataforma_seq", allocationSize = 1)
public class Plataforma implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="plataforma_generator")
	private Long id;
	
	private String nome;

	@Column(name = "indicativo_visual")
	private String indicativoVisual;
	
	@ManyToOne
	@JoinColumn(name = "bandeira")
	private Pais bandeira;
	
	@ManyToOne
	@JoinColumn(name = "FK_TIPO_PLATAFORMA")
	private TipoPlataforma tipoPlataforma;
	
	private boolean ativo;
	
	@Column(name = "abreviacao")
	private String abreviacao;

	
}
