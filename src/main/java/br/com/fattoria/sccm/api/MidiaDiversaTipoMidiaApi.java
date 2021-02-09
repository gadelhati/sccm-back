package br.com.fattoria.sccm.api;

import br.com.fattoria.sccm.persistence.model.MidiaDiversaTipoMidia;
import br.com.fattoria.sccm.persistence.model.MidiaDiversaTipoMidiaPK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class MidiaDiversaTipoMidiaApi {
	
	private Long idMidiaDiversa;
	
	private Long idTipoMidia;
	
	private Long quantidade;
	
	public MidiaDiversaTipoMidia toEntity() {
		return new MidiaDiversaTipoMidia(new MidiaDiversaTipoMidiaPK(idMidiaDiversa, idTipoMidia), quantidade, null, null);
	}

}
