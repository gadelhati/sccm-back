package br.com.fattoria.sccm.reports;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.imageio.ImageIO;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

/**
 * Extender a está classe para criar relatorios
 * T é a classe de dados do relatorio
 * 
 * Sobrescrever o metodo getNameJasperTemplate para informar o caminho padrão do template
 * @author Everton
 *
 * @param <T>
 */
public abstract class ReportGenerator<T> {

	protected final Collection<T> data;
	protected JRFileVirtualizer fileVirtualizer;
	protected InputStream templateJasper;
	protected JasperPrint report;

	protected Map<String, Object> parametros = new HashMap<String, Object>();
	
	abstract String getNameJasperTemplate();
	
	
	/**
	 * 
	 * @param data
	 * @throws FileNotFoundException 
	 */
	protected ReportGenerator(Collection<T> data) throws FileNotFoundException {
		this.data = data;
		parametros.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
		this.setJasperTemplate(getNameJasperTemplate());
	}

	/**
	 * 
	 * @param data
	 * @throws FileNotFoundException 
	 */
	protected ReportGenerator(T... data) throws FileNotFoundException {
		this(Arrays.asList(data));
		this.setJasperTemplate(getNameJasperTemplate());
	}

	
	/**
	 * Retorna pacote base de templates
	 * @return
	 */
	protected String getPackageBaseTemplates() {
		return "/br/com/fattoria/sccm/reports/templates/";
	}
	
	private void setJasperTemplate(String fileNameWithExtension) throws FileNotFoundException {
		templateJasper = getClass().getResourceAsStream(getPackageBaseTemplates() + fileNameWithExtension);
		
		if(templateJasper == null) {
			throw new FileNotFoundException("Arquivo de template não encontrado!");
		}
	}
	
	public void addJasperSubReport(String key, InputStream jasperTemplate) throws JRException{
		if(key != null && key != "" && jasperTemplate != null) {
			parametros.put(key, JRLoader.loadObject(jasperTemplate));
		} else {
			throw new ReportException(new Exception("Informe um template valido para sub report"));
		}
	}
	
	public void addParametro(String key, Object value) {
		parametros.put(key, value);
	}
	

	/**
	 * 
	 * @param arquivo
	 */
	public void geraPDF(String arquivo) {
		geraPDF(new File(arquivo));
	}

	/**
	 * 
	 * @return
	 * @throws JRException
	 */
	protected JasperPrint geraRelatorio() throws JRException {
		if (report == null) {
			fileVirtualizer = new JRFileVirtualizer(100, System.getProperty("java.io.tempdir"));
			fileVirtualizer.cleanup();
			parametros.put(JRParameter.REPORT_VIRTUALIZER, fileVirtualizer);
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
			report = JasperFillManager.fillReport(templateJasper, parametros, dataSource);
		}
		return report;
	}

	/**
	 * 
	 * @param arquivo
	 */
	public void geraPDF(File arquivo) {
		OutputStream out = null;

		try {
			out = new FileOutputStream(arquivo);
			geraPDF(out);

		} catch (FileNotFoundException e) {
			throw new ReportException(e);

		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					throw new ReportException(e);
				}
			}
		}
	}

	/**
	 * 
	 * @param out
	 * @throws IOException 
	 * @throws  
	 */
	public void geraPDF(OutputStream out) {
		try {
			JasperPrint relatorio = geraRelatorio();
			JasperExportManager.exportReportToPdfStream(relatorio, out);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ReportException(e);
		} finally {
			if (out != null) {
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					throw new ReportException(e);
				}
			}
		}
	}

	/**
	 * 
	 * @param arquivo
	 */
	public void geraPNG(String arquivo) {
		geraPNG(new File(arquivo));
	}

	/**
	 * 
	 * @param arquivo
	 */
	public void geraPNG(File arquivo) {
		try {
			geraPNG(new FileOutputStream(arquivo));
		} catch (FileNotFoundException e) {
			throw new ReportException(e);
		}
	}

	/**
	 * 
	 * @return
	 * @throws IOException 
	 */
	public void geraPNG(OutputStream out) {
		try {
			JasperPrint relatorio = geraRelatorio();
			BufferedImage image = (BufferedImage) JasperPrintManager.printPageToImage(relatorio, 0, 2);
			ImageIO.write(image, "png", out);
		} catch (Exception e) {
			throw new ReportException(e);
		} finally {
			if (out != null) {
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					throw new ReportException(e);
				}
			}
		}
	}

	/**
	 * 
	 * @return
	 * @throws IOException 
	 */
	public byte[] geraPDF() throws IOException {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		geraPDF(stream);
		return stream.toByteArray();
	}

	/**
	 * 
	 * @return
	 * @throws IOException 
	 */
	public byte[] geraPNG() throws IOException {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		geraPNG(stream);
		return stream.toByteArray();
	}

	/**
	 * 
	 * @return
	 * @throws IOException 
	 */
	public InputStream geraPDFStream() throws IOException {
		return new ByteArrayInputStream(geraPDF());
	}

	/**
	 * 
	 * @return
	 * @throws IOException 
	 */
	public InputStream geraPNGStream() throws IOException {
		return new ByteArrayInputStream(geraPNG());
	}

	/**
	 * 
	 * @param arquivo
	 */
	public void geraXLS(File arquivo) {
		OutputStream out = null;

		try {
			out = new FileOutputStream(arquivo);
			geraXLS(out);

		} catch (FileNotFoundException e) {
			throw new ReportException(e);

		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					throw new ReportException(e);
				}
			}
		}
	}

	/**
	 * 
	 * @param out
	 */
	public void geraXLS(OutputStream out) {
		try {
			JasperPrint relatorio = geraRelatorio();

			ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();

			SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
			configuration.setOnePagePerSheet(true);
			configuration.setDetectCellType(true);
			configuration.setCollapseRowSpan(false);
			configuration.setWhitePageBackground(false);

			JRXlsExporter exporterXLS = new JRXlsExporter();
			exporterXLS.setExporterInput(new SimpleExporterInput(relatorio));
			exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(xlsReport));
			exporterXLS.setConfiguration(configuration);
			exporterXLS.exportReport();

			byte[] bytes = xlsReport.toByteArray();
			xlsReport.close();
			out.write(bytes, 0, bytes.length);
			out.flush();
			out.close();

		} catch (Exception e) {
			throw new ReportException(e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					throw new ReportException(e);
				}
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	public byte[] geraXLS() {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		geraXLS(stream);
		return stream.toByteArray();
	}

	/**
	 * 
	 * @return
	 */
	public InputStream geraXLSStream() {
		return new ByteArrayInputStream(geraXLS());
	}

}
