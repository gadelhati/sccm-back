package br.com.fattoria.sccm.reports.templates.busines;

import java.util.Calendar;

import br.com.fattoria.sccm.persistence.model.Sequence;

public class SequenceModel {
	
	private String descricao;

	private String prefix;

	private String sufix;

	private Integer ano;
	
	private String sequenceName;
	
	private static SequenceModel model;
	
	public static SequenceModel build(String sequenceName) {
		model = new SequenceModel();
		model.comSequenceName(sequenceName).comAno(0);
		return model;
	}
	
	public SequenceModel comPrefix(String prefix) {
		this.prefix = prefix;
		return model;
	}

	public SequenceModel comSufix(String sufix) {
		this.sufix = sufix;
		return model;
	}

	public SequenceModel comAno(Integer ano) {
		this.ano = ano;
		return model;
	}

	public SequenceModel comDescricao(String descricao) {
		this.descricao = descricao;
		return model;
	}
	
	private SequenceModel comSequenceName(String sequenceName) {
		this.sequenceName = sequenceName;
		return model;
	}
	
	public Sequence getSequenceEntity(){
		Sequence sequence = new Sequence(sequenceName, prefix, sufix, descricao, 0L, 1L, 0L, Calendar.getInstance(), ano);
		return sequence;
	}

	
	

}
