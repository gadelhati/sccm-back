package br.com.fattoria.sccm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(value = "AreaTecnica")
@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class AreaTecnicaController {

}
