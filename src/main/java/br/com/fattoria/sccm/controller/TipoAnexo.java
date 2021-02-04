package br.com.fattoria.sccm.controller;

import java.io.Serializable;

import javax.persistence.Entity;
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
@Table(name = "tipoAnexo")
public class TipoAnexo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String descricao;
	
}
