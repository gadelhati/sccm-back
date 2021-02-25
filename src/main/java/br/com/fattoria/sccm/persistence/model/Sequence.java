package br.com.fattoria.sccm.persistence.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sequence")
public class Sequence implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "codigo")
	private Long codigo;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "atual")
	private Long atual;
	
	@Column(name = "anterior")
	private Long anterior;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data")
	private Calendar data;
	
	@Column(name = "ativo")
	private Boolean ativo;
	
	public void addSequence() {		
		this.anterior =  this.atual;
		this.atual = this.atual++;	
	}
}
