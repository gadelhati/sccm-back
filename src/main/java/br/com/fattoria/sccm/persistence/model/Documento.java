package br.com.fattoria.sccm.persistence.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "documentos")
public class Documento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	private String anexo;
	
	private Calendar dataRecebimento;
	
	private TipoAnexo tipoAnexo;
	
	private Destino destino;
	
	private PesquisaCientifica pesquisaCientifica;
	
	private String observacoes;
		
	
}
