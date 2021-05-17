package br.com.fattoria.sccm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ListagemSynopDTO {
	
	private String primeiroRecebimento;
	
	private String ultimoRecebimento;
	
	private Integer totalModelosRecebidos;
	
	private Integer totalModelosDigitados;
	
	private String estacaoMeteorologica;
	
	private String codigoEstacaoMeteorologica;
	
}
