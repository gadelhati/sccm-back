package br.com.fattoria.sccm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fattoria.sccm.persistence.repository.EquipamentoRepository;
import io.swagger.annotations.Api;

@Api(value = "equipamento")
@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class EquipamentoController {
	
	private final Logger log = LoggerFactory.getLogger(EquipamentoController.class);
	private EquipamentoRepository equipamentoRepository;
	
	public EquipamentoController(EquipamentoRepository equipamentoRepository) {
		this.equipamentoRepository = equipamentoRepository;
	}
	
	

}
