package br.com.fattoria.sccm.persistence.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "midia_diversa")
@SequenceGenerator(name="midia_diversa_generator", sequenceName="midia_diversa_generator_seq", allocationSize = 1)
public class MidiaDiversa {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="midia_diversa_generator")
	private Long id;

	private String conteudo;
	
	@Temporal(TemporalType.DATE)
	private Calendar data;
	
	private boolean documento;
	
	private boolean backup;


}
