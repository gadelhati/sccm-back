package br.com.fattoria.sccm.controller;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fattoria.sccm.entidade.Empresa;
import br.com.fattoria.sccm.service.EmpresaService;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
	
	private EmpresaService empresaService;
	
	@Autowired
	public EmpresaController(EmpresaService empresaService) {
		this.empresaService = empresaService;
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Empresa> cadastrar(@RequestBody Empresa empresa) throws URISyntaxException {
		
		empresaService.cadastrar(empresa);
		
		return null;
	}
	
	@GetMapping("/buscar/{id}")
	public Empresa buscar(@PathVariable String id) {
		return null;
	}
	
	
	
	
}
