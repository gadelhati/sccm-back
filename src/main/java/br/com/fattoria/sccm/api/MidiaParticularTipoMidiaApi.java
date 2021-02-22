package br.com.fattoria.sccm.api;

import br.com.fattoria.sccm.persistence.model.MidiaParticularTipoMidia;
import br.com.fattoria.sccm.persistence.model.MidiaParticularTipoMidiaPK;
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
public class MidiaParticularTipoMidiaApi {
	
	private Long idMidiaParticular;
	
	private Long idTipoMidia;
	
	private Long quantidade;
	
	public MidiaParticularTipoMidia toEntity() {
		return new MidiaParticularTipoMidia(new MidiaParticularTipoMidiaPK(idMidiaParticular, idTipoMidia), quantidade, null, null);
	}

}
