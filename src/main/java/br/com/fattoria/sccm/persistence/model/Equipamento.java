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
@Table(name = "equipamento")
@SequenceGenerator(name = "equipamento_generator", sequenceName = "equipamento_seq", allocationSize = 1)
public class Equipamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "equipamento_generator")
	private Long id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "ativo")
	private boolean ativo;
	
	@Column(name = "sigla")
	private String sigla;
	
	@Column(name = "codigo")
	private String codigo;
	
	@NotNull @NotBlank @NotEmpty
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "fk_area_tecnica")
	private AreaTecnica areaTecnica;
	
	@ManyToOne
	@JoinColumn(name = "fk_metodo_amostragem")
	private MetodoAmostragem metodoAmostragem;
		
}
