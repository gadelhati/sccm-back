package br.com.fattoria.sccm.api;

import java.io.Serializable;

import br.com.fattoria.sccm.persistence.model.Equipamento;

public class EquipamentoApi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String descricao;

	public EquipamentoApi() {
		super();
	}

	public EquipamentoApi(Long id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Equipamento convertToEntity() {
		return new Equipamento(id, descricao);
	}

	@Override
	public String toString() {
		return "EquipamentoApi [id=" + id + ", descricao=" + descricao + "]";
	}

}
