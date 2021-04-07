package br.com.fattoria.sccm.api;

import br.com.fattoria.sccm.persistence.model.XML;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class XMLApi {

	private Long id;
	
	private String nome;
	
	private String xml;
	
	public XML toEntity() {
		return new XML(id, nome, xml);
	}
	
	
}
