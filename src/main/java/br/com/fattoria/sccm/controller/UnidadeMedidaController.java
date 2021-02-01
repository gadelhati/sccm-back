package br.com.fattoria.sccm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fattoria.sccm.persistence.repository.UnidadeMedidaRepository;
import io.swagger.annotations.Api;

@Api(value = "unidadeMedida")
@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class UnidadeMedidaController {
	
	private final Logger log = LoggerFactory.getLogger(UnidadeMedidaController.class);
	private UnidadeMedidaRepository unidadeMedidaRepository;
	
	public UnidadeMedidaController(UnidadeMedidaRepository unidadeMedidaRepository) {	
		this.unidadeMedidaRepository = unidadeMedidaRepository;
	}	

}
