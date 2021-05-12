package br.com.fattoria.sccm.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuantitativoDTO {
	
	private Long quantidade;
	
	private String descricao;
	
	private BigDecimal porcentagem;

}
