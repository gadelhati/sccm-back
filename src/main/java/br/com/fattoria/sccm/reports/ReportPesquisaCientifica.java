package br.com.fattoria.sccm.reports;

import java.io.FileNotFoundException;
import java.util.Collection;

import br.com.fattoria.sccm.reports.data.DataPesquisaCientifica;

/**
 * Exemplo de relatorio
 * @author Everton
 *
 */
public class ReportPesquisaCientifica extends ReportGenerator<DataPesquisaCientifica> {

	public ReportPesquisaCientifica(Collection<DataPesquisaCientifica> data) throws FileNotFoundException {
		super(data);
	}

	String getNameJasperTemplate() {

		return "pesquisacientifica.jasper";
	}

}
