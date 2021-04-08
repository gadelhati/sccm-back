package br.com.fattoria.sccm.xml.sxtream;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import br.com.fattoria.sccm.xml.sxtream.types.CharacterString;
import br.com.fattoria.sccm.xml.sxtream.types.CodeList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("gmd:MD_Keywords")
public class Keyword {
	
	@XStreamAlias("gmd:keyword")
	private CharacterString keyword;
	
	@XStreamAlias("gmd:type")
	private CodeList type;

}
