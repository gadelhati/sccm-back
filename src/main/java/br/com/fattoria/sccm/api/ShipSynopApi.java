package br.com.fattoria.sccm.api;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.fattoria.sccm.persistence.model.Comissao;
import br.com.fattoria.sccm.persistence.model.Destino;
import br.com.fattoria.sccm.persistence.model.PesquisaCientifica;
import br.com.fattoria.sccm.persistence.model.Plataforma;
import br.com.fattoria.sccm.persistence.model.ShipSynop;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ShipSynopApi {

	private Long id;

	private Long idPlataforma;

	private Long idComissao;

	private Calendar periodoInicial;

	private Calendar periodoFinal;

	private Long idPesquisaCientifica;

	private String protocolo;

	private Integer numeroModelos;

	private String dados;

	private Integer numeroInformacoes;

	private Boolean corrigido;

	private Calendar dataEntrada;

	private Calendar dataSaida;

	private String observacoes;
	
	private Long idSituacao;
	
	private Long idEstacaoMeteorologica;

	public ShipSynop toEntity() {
		return new ShipSynop(id, null, null, periodoInicial, periodoFinal, null, protocolo, numeroModelos, dados, numeroInformacoes, corrigido, dataEntrada, dataSaida, observacoes, null, null);
	}
}
