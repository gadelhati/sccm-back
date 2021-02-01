package br.com.fattoria.sccm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fattoria.sccm.persistence.repository.AreaTecnicaRepository;
import br.com.fattoria.sccm.persistence.repository.EquipamentoRepository;
import io.swagger.annotations.Api;

@Api(value = "AreaTecnica")
@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class AreaTecnicaController {
	
	private final Logger log = LoggerFactory.getLogger(AreaTecnicaController.class);
	private AreaTecnicaRepository areaTecnicaRepository;
		
	public AreaTecnicaController(AreaTecnicaRepository areaTecnicaRepository) {
		this.areaTecnicaRepository = areaTecnicaRepository;
	}

	
	
}
