package br.com.fattoria.sccm.persistence.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
@Table(name = "midia_diversa")
@SequenceGenerator(name="midia_diversa_generator", sequenceName="midia_diversa_generator_seq", allocationSize = 1)
public class MidiaDiversa {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="midia_diversa_generator")
	private Long id;

	@NotNull @NotBlank @NotEmpty
	private String conteudo;
	
	@Temporal(TemporalType.DATE)
	private Calendar data;
	
	private boolean documento;
	
	private boolean backup;

	@Column(name = "numero_oficio")
	private String numeroOficio;
	
	@Column(name = "numero_h")
	private String numeroH;
	
	private Long codigo;
	
	@OneToOne
	@JoinColumn(name = "fk_situacao")
	private Situacao situacao;

}
