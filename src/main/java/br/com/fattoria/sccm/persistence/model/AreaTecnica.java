package br.com.fattoria.sccm.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
@Table(name = "area_tecnica")
@SequenceGenerator(name="area_tecnica_generator", sequenceName="area_tecnica_seq", allocationSize = 1)
public class AreaTecnica implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="area_tecnica_generator")
	private Long id;
	
	@NotNull
	@NotBlank
	@NotEmpty
	@Column(name = "descricao")
	private String descricao;
	
	private boolean ativo;

}
