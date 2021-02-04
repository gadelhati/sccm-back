package br.com.fattoria.sccm.persistence.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.fattoria.sccm.controller.TipoAnexo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "documentos")
public class Documentos implements Serializable {

	private Long id;
	
	private String anexo;
	
	private Calendar dataRecebimento;
	
	private TipoAnexo tipoAnexo;
	
	private Destino destino;
	
}
