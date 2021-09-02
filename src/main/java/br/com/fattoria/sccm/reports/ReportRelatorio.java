package br.com.fattoria.sccm.reports;

import java.io.FileNotFoundException;

import br.com.fattoria.sccm.reports.data.RelatoriosDTO;

public class ReportRelatorio  extends ReportGenerator<RelatoriosDTO> {

	public ReportRelatorio(RelatoriosDTO relatorio) throws FileNotFoundException {
		super(relatorio);
	}
	
	@Override
	String getNameJasperTemplate() {	
		return "relatorioPDFMarinha.jasper";
	}

}
