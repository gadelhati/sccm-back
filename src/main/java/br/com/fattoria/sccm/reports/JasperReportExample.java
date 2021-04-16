package br.com.fattoria.sccm.reports;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class JasperReportExample {
	
	private static final String url = "";
	
	private static final String driver = "";
	
	private static final String login = "";
	
	private static final String pwd = "";

	
	public void gerar(String layout) throws JRException, ClassNotFoundException, SQLException {
		
		//Gera o Design
		JasperDesign design = JRXmlLoader.load(layout);
		
		//compila o Relatório
		JasperReport relatorio = JasperCompileManager.compileReport(design);
		
		
//		Class.forName(driver);
//		Connection con = DriverManager.getConnection(url, login, pwd);
//		Statement stm = con.createStatement();
//		String query = " select * from ficha_pesquisa_cientifica_view where id = 521";
//		ResultSet rs = stm.executeQuery(query);
		
	}
	
}
