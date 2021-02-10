package br.com.fattoria.sccm.persistence.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "documentos")
@SequenceGenerator(name="documento_generator", sequenceName="documento_seq", allocationSize = 1)
public class Documento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="documento_generator")
	private Long id;
	
	private String anexo;
	
	@Column(name = "data_recebimento")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataRecebimento;
	
	@ManyToOne
	@JoinColumn(name = "fk_tipo_anexo")
	private TipoAnexo tipoAnexo;
	
	@ManyToOne
	@JoinColumn(name = "fk_destino")
	private Destino destino;
	
	@ManyToOne
	@JoinColumn(name = "fk_pesquisa_cientifica")
	private PesquisaCientifica pesquisaCientifica;
	
	private String observacoes;
		
	
}
