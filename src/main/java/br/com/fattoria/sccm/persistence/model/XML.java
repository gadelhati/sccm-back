package br.com.fattoria.sccm.persistence.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "xml")
@Getter @Setter @EqualsAndHashCode @ToString @NoArgsConstructor @AllArgsConstructor
public class XML implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	private String nome;
	
	private String xml;
	
	public String getFormat(PesquisaCientifica pc) {
		
		this.xml = this.xml.replace("${FILE_IDENTIFIER}", "");		
		this.xml = this.xml.replace("${DATA_GERACAO_XML}", "");		
		this.xml = this.xml.replace("${TITULO}", "");
		this.xml = this.xml.replace("${DATA_RECEBIMENTO}", "");
		this.xml = this.xml.replace("${RESUMO}", "");
		this.xml = this.xml.replace("${NAVIO}", "");
		this.xml = this.xml.replace("${COORDENADOR_PROJETO}", "");
		this.xml = this.xml.replace("${INSTITUICAO}", "");
		this.xml = this.xml.replace("${CAMINHO_PC}", "");		 
		this.xml = this.xml.replace("${LIMITE_OESTE}", ""); 
		this.xml = this.xml.replace("${LIMITE_LESTE}", "");
		this.xml = this.xml.replace("${LIMITE_SUL}", "");
		this.xml = this.xml.replace("${LIMITE_NORTE}", "");
		this.xml = this.xml.replace("${DATA_INICIO}", "");
		this.xml = this.xml.replace("${DATA_FINAL}", "");	    
		
		String conteudoDinamico = "";
		
		if (pc.getListaAreaConhecimento() != null && pc.getListaAreaConhecimento().size() > 0) {			
			for (AreaConhecimento areaConhecimento : pc.getListaAreaConhecimento()) {				
				conteudoDinamico += this.getConteudoDinamico(this.xml, areaConhecimento.getNome());				
			}			
		}
		
		this.xml = this.
		
		return "";
	}
	
	private String getConteudoDinamico(String conteudo, String palavraChave) {
		
		boolean catchContent = false;
		
		String[] linha = conteudo.split("\n");
		
		String conteudoDinamico = "";
		
		for (String l : linha) {
			
			if (conteudo.contains("${FIM_FOR_PALAVRA_CHAVE}")) {
				catchContent = false;
			}
		
			if (catchContent) {
				conteudoDinamico += conteudo + "\n";
			}
		
			if (conteudo.contains("${FOR_PALAVRA_CHAVE}")) {
				catchContent = true;
			}	
		}
				
		return conteudoDinamico.replace("${PALAVRA_CHAVE}", palavraChave);
	}
	
	
	public static void main(String[] args) {

		boolean ler = true;

		boolean catchContent = false;

		try {
			BufferedReader buffRead = new BufferedReader(new FileReader("C:\\Users\\Victor\\Downloads\\teste.xml"));
			String linha = buffRead.readLine();

			String conteudoDinamico = "";
			
			while (ler) {

				if (linha != null && !linha.equals("")) {
					
					
					/*if (linha.contains("${FIM_FOR_PALAVRA_CHAVE}")) {
						catchContent = false;
					}
					
					if (catchContent) {*/
						conteudoDinamico += linha + "\n";
					/*}
					
					if (linha.contains("${FOR_PALAVRA_CHAVE}")) {
						catchContent = true;
					}*/					
//					System.out.println(i++ + linha);
				} else {
					ler = false;
				}
				
				linha = buffRead.readLine();

			}
			
//			System.out.println(conteudoDinamico);
			
			String[] split = conteudoDinamico.split("\n");
			
			for (String s : split) {
				System.out.println(s);
			}
			

			buffRead.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("FIM");

	}
	
}
