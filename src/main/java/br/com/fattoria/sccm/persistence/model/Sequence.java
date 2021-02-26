package br.com.fattoria.sccm.persistence.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.util.ObjectUtils;

import br.com.fattoria.sccm.persistence.listener.SequenceListener;
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
	@Column(name = "chave")
	private String chave;
	
	@Column(name = "prefix")
	private String prefix;
	
	@Column(name = "sufix")
	private String sufix;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "atual")
	private Long atual;
	
	@Version
	@Column(name = "version")
	private Long version;
	
	@Column(name = "anterior")
	private Long anterior;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data")
	private Calendar data;
	
	@Column(name = "ano")
	private Integer ano;
	
	public void addSequence() {		
		this.anterior =  this.atual;
		this.atual = this.atual +1;	
	}

	public String getSequence() {
		StringBuilder seq = new StringBuilder();
		
		if(!ObjectUtils.isEmpty(prefix)) {
			seq.append(prefix);
		}
		seq.append(atual);
		if(!ObjectUtils.isEmpty(sufix)) {
			seq.append(sufix);
		}
		return seq.toString();
	}
}
