package br.com.fattoria.sccm.persistence.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "usuario")
@Getter @Setter @EqualsAndHashCode @ToString @NoArgsConstructor
public class Usuario {

	@Id
	private Long id;

	@NotBlank(message = "O nome é obrigatório")
	private String nome;

}
