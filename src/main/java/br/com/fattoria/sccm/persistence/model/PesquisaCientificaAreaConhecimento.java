package br.com.fattoria.sccm.persistence.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pesquisaCientificaAreaConhecimento")
public class PesquisaCientificaAreaConhecimento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private PesquisaCientificaAreaConhecimentoPk pesquisaCientificaAreaConhecimentoPk;
	
	@ManyToOne
	@JoinColumn(name = "fk_pesquisa_cientifica")
	private PesquisaCientifica pesquisaCientifica;
	
	@ManyToOne
	@JoinColumn(name = "fk_area_conhecimento")
	private AreaConhecimento areaConhecimento;
}
