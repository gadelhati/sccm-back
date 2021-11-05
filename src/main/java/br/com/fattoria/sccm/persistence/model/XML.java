package br.com.fattoria.sccm.persistence.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.fattoria.sccm.xml.sxtream.DescriptiveKeywords;
import br.com.fattoria.sccm.xml.sxtream.Keyword;
import br.com.fattoria.sccm.xml.sxtream.types.CharacterString;
import br.com.fattoria.sccm.xml.sxtream.types.CodeList;
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
		
		SimpleDateFormat dateTimeFormatXML = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		SimpleDateFormat dateFormatXML = new SimpleDateFormat("yyyy-MM-dd");
		
		this.xml = this.xml.replace("${FILE_IDENTIFIER}", pc.getId().toString());		
		this.xml = this.xml.replace("${DATA_GERACAO_XML}", dateTimeFormatXML.format(new Date()));		
		this.xml = this.xml.replace("${TITULO}", pc.getComissao().getNomeComissao());
		this.xml = this.xml.replace("${DATA_RECEBIMENTO}", pc.getDataCadastro() != null ? dateTimeFormatXML.format(pc.getDataCadastro().getTime()) : "");
		this.xml = this.xml.replace("${RESUMO}", pc.getComissao().getResumo());
		this.xml = this.xml.replace("${NAVIO}", pc.getPlataforma().getNome());
		this.xml = this.xml.replace("${COORDENADOR_PROJETO}", pc.getCoordenadorCientifico());
		this.xml = this.xml.replace("${INSTITUICAO}", pc.getInstituicao().getNome());
		this.xml = this.xml.replace("${ID_PC}", pc.getId().toString());	
		this.xml = this.xml.replace("${NUMERO_PC}", pc.getNumeroPC());	
		this.xml = this.xml.replace("${LIMITE_OESTE}", pc.getLimiteOesteLongitude()); 
		this.xml = this.xml.replace("${LIMITE_LESTE}", pc.getLimiteLesteLongitude());
		this.xml = this.xml.replace("${LIMITE_SUL}", pc.getLimiteSulLatitude());
		this.xml = this.xml.replace("${LIMITE_NORTE}", pc.getLimiteNorteLatitude());
		this.xml = this.xml.replace("${DATA_INICIO}", dateFormatXML.format(pc.getComissao().getDataInicio().getTime()));
		this.xml = this.xml.replace("${DATA_FINAL}", dateFormatXML.format(pc.getComissao().getDataFim().getTime()));	  
		
		StringBuilder conteudoDinamico = new StringBuilder("");
		
		if (pc.getListaAreaConhecimento() != null && pc.getListaAreaConhecimento().size() > 0) {			
			for (AreaConhecimento areaConhecimento : pc.getListaAreaConhecimento()) {				
				CharacterString keyword = new CharacterString(areaConhecimento.getNome());
				CodeList type = new CodeList("platform");
				DescriptiveKeywords descriptiveKeywords = new DescriptiveKeywords(new Keyword(keyword, type));
				conteudoDinamico.append(descriptiveKeywords.toXML());
				conteudoDinamico.append("\n");
			}			
		}
		
		if (pc.getPlataforma() != null) {			
			CharacterString keyword = new CharacterString(pc.getPlataforma().getNome());
			CodeList type = new CodeList("platform");
			DescriptiveKeywords descriptiveKeywords = new DescriptiveKeywords(new Keyword(keyword, type));
			conteudoDinamico.append(descriptiveKeywords.toXML());	
			conteudoDinamico.append("\n");
		}
		
		if (pc.getListaTiposDados() != null && pc.getListaTiposDados().size() > 0) {		
			for (TipoDado tipoDado : pc.getListaTiposDados()) {
				CharacterString keyword = new CharacterString(tipoDado.getDescricao());
				CodeList type = new CodeList("platform");//sensor
				DescriptiveKeywords descriptiveKeywords = new DescriptiveKeywords(new Keyword(keyword, type));
				conteudoDinamico.append(descriptiveKeywords.toXML());	
				conteudoDinamico.append("\n");
			}
		}
		
		if (pc.getListaEquipamentos() != null && pc.getListaEquipamentos().size() > 0) {		
			for (Equipamento equipamento : pc.getListaEquipamentos()) {
				CharacterString keyword = new CharacterString(equipamento.getNome());
				CodeList type = new CodeList("instrument");
				DescriptiveKeywords descriptiveKeywords = new DescriptiveKeywords(new Keyword(keyword, type));
				conteudoDinamico.append(descriptiveKeywords.toXML());	
				conteudoDinamico.append("\n");
			}
		}

		if (pc.getComissao().getPalavrasChaves() != null && pc.getComissao().getPalavrasChaves() != "") {	
			
			String[] palavrasChaves = pc.getComissao().getPalavrasChaves().split(",");
			
			if(palavrasChaves.length > 0) {
				for (String palavraChave : palavrasChaves) {
					CharacterString keyword = new CharacterString(palavraChave);
					CodeList type = new CodeList("theme");
					DescriptiveKeywords descriptiveKeywords = new DescriptiveKeywords(new Keyword(keyword, type));
					conteudoDinamico.append(descriptiveKeywords.toXML());
					conteudoDinamico.append("\n");
				}
			}
			
		}
		
		conteudoDinamico.append("<!--PALAVRAS CHAVES -->");
		
		this.xml = this.xml.replace("${PALAVRA_CHAVE}", conteudoDinamico.toString());
		
		return xml;
	}
	
}
