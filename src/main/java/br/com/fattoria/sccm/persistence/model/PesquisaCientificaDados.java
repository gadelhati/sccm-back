package br.com.fattoria.sccm.persistence.model;

import java.io.Serializable;

public class PesquisaCientificaDados implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private PesquisaCientifica pesquisaCientifica;
	
	private TipoDado tipoDado;
	
	private int quantidade;
	
}
