package br.com.fattoria.sccm.reports;

import java.io.FileNotFoundException;

import br.com.fattoria.sccm.reports.data.FichaPesquisaCientificaDTO;

public class ReportFichaPesquisaCientifica extends ReportGenerator<FichaPesquisaCientificaDTO> {

	public ReportFichaPesquisaCientifica(FichaPesquisaCientificaDTO dto) throws FileNotFoundException {
		super(dto);
	}
	
	@Override
	String getNameJasperTemplate() {
		return "fichaPesquisaCientifica.jasper";
	}

}
