package br.com.fattoria.sccm.dto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import br.com.fattoria.sccm.api.Periodo;
import br.com.fattoria.sccm.persistence.model.PesquisaCientifica;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PendenciaDTO {
	
	public PendenciaDTO(PesquisaCientifica pesquisaCientifica, Periodo periodo) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		this.pc = pesquisaCientifica.getNumeroPC();
		this.instituicao = pesquisaCientifica.getInstituicao().getNome();
		this.plataforma = pesquisaCientifica.getPlataforma().getNome();
		this.dataInicio = sdf.format(periodo.getDataInicio());
		this.dataFim = sdf.format(periodo.getDataFim());
		
	}

	private String pc;
	
	private String instituicao;
	
	private String plataforma;
	
	private String dataInicio;
	
	private String dataFim;
	
}
