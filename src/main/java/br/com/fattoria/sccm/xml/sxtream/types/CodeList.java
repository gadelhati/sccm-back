package br.com.fattoria.sccm.xml.sxtream.types;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
public class CodeList {
	
	public CodeList() {
		super();
		this.codeList = "http://standards.iso.org/ittf/PubliclyAvailableStandards/ISO_19139_Schemas/resources/codelist/ML_gmxCodelists.xml#MD_KeywordTypeCode";
	}

	public CodeList(String codeListValue) {
		this();
		this.codeListValue = codeListValue;
	}

	@XStreamAsAttribute 
	private String codeList;

	@XStreamAsAttribute 
	private String codeListValue;
}
