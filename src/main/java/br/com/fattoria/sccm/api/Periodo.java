package br.com.fattoria.sccm.api;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.com.fattoria.sccm.persistence.domain.PeriodoData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Periodo implements PeriodoData {
	
	private Calendar dataInicio;
	
	private Calendar dataFim;
	
	public String toString(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		StringBuilder stringBuilder = new StringBuilder();
		if(dataInicio != null)
			stringBuilder.append("Data Inicio " + dateFormat.format(dataInicio.getTime()));
		if(dataFim != null)
			stringBuilder.append("Data Fim " + dateFormat.format(dataFim.getTime()));
		
		return stringBuilder.toString();
	}

}
