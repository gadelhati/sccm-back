package br.com.fattoria.sccm.persistence.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
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
	
	@JoinColumn(name = "fk_area_tecnica")
	private AreaTecnica areaTecnica;
	
	@JoinColumn(name = "fk_metodo_amostragem")
	private MetodoAmostragem metodoAmostragem;
	
	@JoinColumn(name = "fk_unidade_medida")
	private UnidadeMedida unidadeMedida;
		
}
