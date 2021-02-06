package br.com.fattoria.sccm.persistence.model;

import java.io.Serializable;
import java.util.Calendar;

public class Comissao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String numeroPC;
	
	private String nomeComissao;
	
	private String nomeCruzeiro;
	
	private Integer pernada;
	
	private Calendar dataEntrada;
	
	private Calendar dataOficial;
	
	private Calendar dataInicio;
	
	private Calendar dataFim;
	
	private String observacoes;
	
	private String imagem;

}
