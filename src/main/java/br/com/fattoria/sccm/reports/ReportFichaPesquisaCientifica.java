package br.com.fattoria.sccm.reports;

import java.io.FileNotFoundException;
import java.util.Collection;

public class ReportFichaPesquisaCientifica extends ReportGenerator<Long> {

	public ReportFichaPesquisaCientifica(Collection<Long> idPC) throws FileNotFoundException {
		super(idPC);
	}
	
	@Override
	String getNameJasperTemplate() {
		return "fichaPesquisaCientifica.jasper";
	}

}
