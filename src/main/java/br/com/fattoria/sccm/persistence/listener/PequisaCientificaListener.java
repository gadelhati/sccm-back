package br.com.fattoria.sccm.persistence.listener;

import java.util.Calendar;

import javax.persistence.PrePersist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import br.com.fattoria.sccm.persistence.model.PesquisaCientifica;

@Component
public class PequisaCientificaListener {

	private final Logger log = LoggerFactory.getLogger(PequisaCientificaListener.class);

	@PrePersist
	void dataCriacao(PesquisaCientifica pesquisaCientifica) {
		log.info("Colocando data de cadastro ");
		pesquisaCientifica.setDataCadastro(Calendar.getInstance());
	}

}
