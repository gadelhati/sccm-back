package br.com.fattoria.sccm.persistence.model;

import java.util.List;

import javax.persistence.CascadeType;
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
public class Equipamento {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "equipamento_generator")
	private Long id;

	@NotNull @NotBlank @NotEmpty
	private String descricao;

	private boolean ativo;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "area_conhecimento_equipamento", 
			   joinColumns = {@JoinColumn(name = "fk_equipamento")},
			   inverseJoinColumns = {@JoinColumn(name = "fk_area_conhecimento")}
	)
	private List<AreaConhecimento> listaAreaConhecimento;

}
