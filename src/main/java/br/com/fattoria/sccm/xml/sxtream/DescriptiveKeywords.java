package br.com.fattoria.sccm.xml.sxtream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import br.com.fattoria.sccm.xml.sxtream.types.CodeList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("gmd:descriptiveKeywords")
public class DescriptiveKeywords {
	
	@XStreamAlias("gmd:MD_Keywords")
	private Keyword keyword;
	
	public String toXML() {
		
		XStream xstream = new XStream();
		xstream.autodetectAnnotations(true);
		
//		xstream.alias("gmd:descriptiveKeywords", DescriptiveKeywords.class);
//		xstream.aliasField("gmd:MD_Keywords", DescriptiveKeywords.class, "keyword");
//		
//		xstream.aliasField("gmd:keyword", Keyword.class, "keyword");
//		xstream.aliasField("gmd:type", Keyword.class, "type");
		
		xstream.useAttributeFor(CodeList.class, "codeList");
		xstream.useAttributeFor(CodeList.class, "codeListValue");
		String xml = xstream.toXML(this);
		
		return xml.replaceAll("MD__", "MD_");
	}

}
