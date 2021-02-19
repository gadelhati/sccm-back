package br.com.fattoria.sccm.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChaveValor<K, V> {
	
	private K chave;
	
	private V valor;	
	

}
