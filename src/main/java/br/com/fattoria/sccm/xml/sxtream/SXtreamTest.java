package br.com.fattoria.sccm.xml.sxtream;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.thoughtworks.xstream.XStream;

import br.com.fattoria.sccm.xml.sxtream.types.CharacterString;
import br.com.fattoria.sccm.xml.sxtream.types.CodeList;

public class SXtreamTest {

	public static void main(String[] args) {

	
			//FileReader fileReader = new FileReader("");
			
			CharacterString keyword = new CharacterString("SHIP [22]");
			CodeList type = new CodeList("instrument");
			
			DescriptiveKeywords descriptiveKeywords = new DescriptiveKeywords(new Keyword(keyword, type));
			
//			XStream xstream = new XStream();
//			xstream.alias("gmd:descriptiveKeywords", DescriptiveKeywords.class);
//			xstream.aliasField("gmd:MD_Keywords", DescriptiveKeywords.class, "keyword");
//			
//			xstream.aliasField("gmd:keyword", Keyword.class, "keyword");
//			xstream.aliasField("gmd:type", Keyword.class, "type");
//			
//			xstream.useAttributeFor(CodeList.class, "codeList");
//			xstream.useAttributeFor(CodeList.class, "codeListValue");
//			String xml = xstream.toXML(descriptiveKeywords);
			
			//System.out.println(descriptiveKeywords.toXML());
			
			SimpleDateFormat dateFormatXML = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			
			System.out.println(dateFormatXML.format(new Date()));

	}
	
	/**
<gmd:descriptiveKeywords>
<!--  MUDA: Palavras Chave, lembrar de separar as palavras chave; Colocar: Área do conhecimento, parâmetros oceanográficos, equipamentos oceanográficos, equipamentos geofísicos, palavras chaves, nome da plataforma  -->
	<gmd:MD_Keywords>
	
		<gmd:keyword>
			<gco:CharacterString>SHIP [22]</gco:CharacterString>
		</gmd:keyword>
		
		<gmd:type>
			<gmd:MD_KeywordTypeCode codeList="http://standards.iso.org/ittf/PubliclyAvailableStandards/ISO_19139_Schemas/resources/codelist/ML_gmxCodelists.xml#MD_KeywordTypeCode" codeListValue="instrument"/>
		</gmd:type>
		
	</gmd:MD_Keywords>
</gmd:descriptiveKeywords>	 
	 */

}
