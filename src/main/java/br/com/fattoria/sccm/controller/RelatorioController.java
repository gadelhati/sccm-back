package br.com.fattoria.sccm.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fattoria.sccm.api.Periodo;
import br.com.fattoria.sccm.persistence.repository.RelatorioRepository;
import br.com.fattoria.sccm.reports.ReportRelatorio;
import br.com.fattoria.sccm.reports.data.RelatoriosDTO;
import io.swagger.annotations.Api;
import net.sf.jasperreports.engine.JRException;

@Api(value = "relatorio")
@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class RelatorioController {

	private RelatorioRepository relatorioRepository;
	
	public RelatorioController(RelatorioRepository relatorioRepository) {		
		this.relatorioRepository = relatorioRepository;
	}

	@PostMapping({"/relatorio/pdf"})
	public ResponseEntity<?> getPDF(@RequestBody Periodo periodo) throws IOException, JRException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		RelatoriosDTO dto = new RelatoriosDTO();
		
		dto.setPeriodoInicial(sdf.format(periodo.getDataInicio().getTime()));
		dto.setPeriodoFinal(sdf.format(periodo.getDataFim().getTime()));
		
		dto.setQtdPCRecebidosNovo(relatorioRepository.countByDataCadastroBetween(periodo).intValue());
		
		dto.setTiposComissao(relatorioRepository.countByDataCadastroBetweenGroupByTipoComissao(periodo));
		
		dto.setDadosEquipamentosRecebidos(relatorioRepository.countByDataCadastroBetweenGroupByEquipamentos(periodo));
		
		dto.setPorSituacao(relatorioRepository.countByDataCadastroBetweenGroupBySituacao(periodo));
		
		dto.setPesquisaRecebidasPendentes(relatorioRepository.findByDataCadastroIdSituacaoBetween(periodo, 4L));
	
		dto.setShipModeloPorSituacoes(relatorioRepository.sumModelosObservacoesMeteorologicasByDataCadastroBetweenGroupBySituacao(periodo, "ship"));
		
		dto.setShipInformacoesPorSituacoes(relatorioRepository.sumInformacaoObservacoesMeteorologicasByDataCadastroBetweenGroupBySituacao(periodo, "ship"));
		
		dto.setSynopModeloPorSituacoes(relatorioRepository.sumModelosObservacoesMeteorologicasByDataCadastroBetweenGroupBySituacao(periodo, "synop"));
		
		dto.setSynopInformacoesPorSituacoes(relatorioRepository.sumInformacaoObservacoesMeteorologicasByDataCadastroBetweenGroupBySituacao(periodo, "synop"));
		
		dto.setDadosEstacoesMeteorologicas(relatorioRepository.listagemDadosEstacoesMeteorologicasByDataCadastroBetweenGroupBySituacao(periodo));
		
		dto.setMidiasParticularesPorSituacoes(relatorioRepository.countMidiasParticularesByDataCadastroBetweenGroupBySituacao(periodo));
		
		dto.setMidiasDiversasPorSituacoes(relatorioRepository.countMidiasDiversasByDataCadastroBetweenGroupBySituacao(periodo));
		
		
		ReportRelatorio report = new ReportRelatorio(dto);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {			
			report.geraPDF(baos);
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		report.addParametro("IMG_LOGO", ReportFichaPesquisaCientifica.class.
				getResourceAsStream("/br/com/fattoria/sccm/reports/img/logo-chm.png"));
		
		byte[] arquivo = report.geraPDF();
		
		FileOutputStream fos = new FileOutputStream("E:\\Everton\\Área de Trabalho\\arq"+System.currentTimeMillis()+".pdf");
		fos.write(arquivo);
		fos.flush();
		fos.close();*/
		
		//report.geraPDF("E:\\Everton\\Área de Trabalho\\teste"+System.currentTimeMillis()+".pdf");
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType("application/pdf"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Relatorio.pdf\"")								
				.body(baos);
		
	}
	
}
