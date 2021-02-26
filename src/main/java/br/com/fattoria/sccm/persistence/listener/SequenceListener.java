package br.com.fattoria.sccm.persistence.listener;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.fattoria.sccm.persistence.model.Sequence;

public class SequenceListener {

	private final Logger log = LoggerFactory.getLogger(SequenceListener.class);

	@PreUpdate @PrePersist
	void dataCriacao(Sequence sequence) {
		log.info("incrementando sequence ");
		sequence.setAnterior(sequence.getAtual());
		sequence.setAtual(sequence.getAtual() +1);
	}

}
