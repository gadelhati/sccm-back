package br.com.fattoria.sccm.api;

import java.util.Calendar;

import br.com.fattoria.sccm.persistence.model.MidiaDiversa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class MidiaDiversaApi {
	
	private Long id;
	
	private String conteudo;
	
	private Calendar data;
	
	private boolean documento;
	
	private boolean backup;
	
	public MidiaDiversa toEntity() {
		return new MidiaDiversa(id, conteudo, data, documento, backup);
	}

}
