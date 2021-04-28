package br.com.fattoria.sccm.api;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.fattoria.sccm.persistence.model.AssinaturaPC;
import br.com.fattoria.sccm.persistence.model.Destino;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AssinaturaPCApi {

	
	private Long id;	
	
	private String nome;
	
	private Long idDestino;
	
	private Boolean ativo;
	
	public AssinaturaPC toEntity() {
		return new AssinaturaPC(id, nome, null, ativo);
	}
	
}
