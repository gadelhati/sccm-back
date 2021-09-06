package br.com.fattoria.sccm.controller;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fattoria.sccm.api.Periodo;
import br.com.fattoria.sccm.converter.CalendarConverter;
import br.com.fattoria.sccm.dto.PendenciaDTO;
import br.com.fattoria.sccm.persistence.model.PesquisaCientifica;
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

	@GetMapping({"/relatorio/pdf"})
	public ResponseEntity<?> getPDF(@RequestParam String dataInicio, @RequestParam String dataFim) throws IOException, JRException {
		
		Periodo periodo = new Periodo(new GregorianCalendar(2021, Calendar.JANUARY, 1), new GregorianCalendar(2021, Calendar.SEPTEMBER, 1));
		
		Calendar dataInicioCalendar = CalendarConverter.parseToCalendar(dataInicio, "yyyy-MM-dd");
		Calendar dataFimCalendar = CalendarConverter.parseToCalendar(dataInicio, "yyyy-MM-dd");
		
		RelatoriosDTO dto = new RelatoriosDTO();
		
		dto.setPeriodoInicial(CalendarConverter.parseToString(dataInicioCalendar, "dd/MM/yyyy"));
		dto.setPeriodoFinal(CalendarConverter.parseToString(dataFimCalendar, "dd/MM/yyyy"));
		
		dto.setQtdPCRecebidosNovo(relatorioRepository.countByDataCadastroBetween(periodo).intValue());
		
		dto.setTiposComissao(relatorioRepository.countByDataCadastroBetweenGroupByTipoComissao(periodo));
		
		dto.setDadosEquipamentosRecebidos(relatorioRepository.countByDataCadastroBetweenGroupByEquipamentos(periodo));
		
		dto.setPorSituacao(relatorioRepository.countByDataCadastroBetweenGroupBySituacao(periodo));
		
		Collection<PesquisaCientifica> pcs = relatorioRepository.findByDataCadastroIdSituacaoBetween(periodo, 4L);
		Collection<PendenciaDTO> listaPendencia = new LinkedList<PendenciaDTO>();
		
		if (pcs != null && !pcs.isEmpty()) {
			
			for (PesquisaCientifica pesquisaCientifica : pcs) {
				
				PendenciaDTO pendencia = new PendenciaDTO(pesquisaCientifica, periodo);		
				listaPendencia.add(pendencia);
			}
			
		}
						
		dto.setPesquisaRecebidasPendentes(listaPendencia);
	
		dto.setShipModeloPorSituacoes(relatorioRepository.sumModelosObservacoesMeteorologicasByDataCadastroBetweenGroupBySituacao(periodo, "ship"));
		
		dto.setShipInformacoesPorSituacoes(relatorioRepository.sumInformacaoObservacoesMeteorologicasByDataCadastroBetweenGroupBySituacao(periodo, "ship"));
		
		dto.setSynopModeloPorSituacoes(relatorioRepository.sumModelosObservacoesMeteorologicasByDataCadastroBetweenGroupBySituacao(periodo, "synop"));
		
		dto.setSynopInformacoesPorSituacoes(relatorioRepository.sumInformacaoObservacoesMeteorologicasByDataCadastroBetweenGroupBySituacao(periodo, "synop"));
		
		/*dto.setDadosEstacoesMeteorologicas(relatorioRepository.listagemDadosEstacoesMeteorologicasByDataCadastroBetweenGroupBySituacao(periodo));*/
		
		dto.setMidiasParticularesPorSituacoes(relatorioRepository.countMidiasParticularesByDataCadastroBetweenGroupBySituacao(periodo));
		
		dto.setMidiasDiversasPorSituacoes(relatorioRepository.countMidiasDiversasByDataCadastroBetweenGroupBySituacao(periodo));
				
		ReportRelatorio report = new ReportRelatorio(dto);

		InputStream in = RelatoriosDTO.class.
				getResourceAsStream("/br/com/fattoria/sccm/reports/img/logo-chm.png");

		report.addParametro("IMG_LOGO", in);
		
		report.addJasperSubReport("PARAM_TIPOS_COMISSAO", RelatoriosDTO.class
				.getResourceAsStream("/br/com/fattoria/sccm/reports/templates/subreportTiposComissoes.jasper"));
		
		report.addJasperSubReport("PARAM_DADOS_RECEBIDOS", RelatoriosDTO.class
				.getResourceAsStream("/br/com/fattoria/sccm/reports/templates/subreportDadosRecebidos.jasper"));
		
		report.addJasperSubReport("PARAM_SITUACAO", RelatoriosDTO.class
				.getResourceAsStream("/br/com/fattoria/sccm/reports/templates/subreportSituacao.jasper"));
		
		report.addJasperSubReport("PARAM_PENDENCIA", RelatoriosDTO.class
				.getResourceAsStream("/br/com/fattoria/sccm/reports/templates/subreportPendencia.jasper"));
		
		report.addJasperSubReport("PARAM_MOD_SHIP", RelatoriosDTO.class
				.getResourceAsStream("/br/com/fattoria/sccm/reports/templates/subreportQTDModeloSHIP.jasper"));
		
		report.addJasperSubReport("PARAM_INF_SHIP", RelatoriosDTO.class
				.getResourceAsStream("/br/com/fattoria/sccm/reports/templates/subreportQTDInfSHIP.jasper"));
		
		report.addJasperSubReport("PARAM_MOD_SYNOP", RelatoriosDTO.class
				.getResourceAsStream("/br/com/fattoria/sccm/reports/templates/subreportQTDModelosSYNOP.jasper"));
		
		report.addJasperSubReport("PARAM_INF_SYNOP", RelatoriosDTO.class
				.getResourceAsStream("/br/com/fattoria/sccm/reports/templates/subreportQTDInfSYNOP.jasper"));
		
		report.addJasperSubReport("PARAM_EMP_PARTICULAR", RelatoriosDTO.class
				.getResourceAsStream("/br/com/fattoria/sccm/reports/templates/subreportQTDInfSYNOP.jasper"));
		
		report.addJasperSubReport("PARAM_DIVERSOS", RelatoriosDTO.class
				.getResourceAsStream("/br/com/fattoria/sccm/reports/templates/subreportQTDInfSYNOP.jasper"));

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {			
			report.geraPDF(baos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		byte[] arquivo = report.geraPDF();
		
		FileOutputStream fos = new FileOutputStream("C:\\Users\\Victor\\Desktop\\arq"+System.currentTimeMillis()+".pdf");
		fos.write(arquivo);
		fos.flush();
		fos.close();
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType("application/pdf"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Relatorio.pdf\"")								
				.body(baos);
		
	}
	
}
