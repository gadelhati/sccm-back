package br.com.fattoria.sccm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fattoria.sccm.persistence.repository.PlataformaRepository;

@RestController
@RequestMapping(name = "/plataforma")
public class PlataformaController {

	private PlataformaRepository plataformaRepository;
	
	@Autowired
	public PlataformaController(PlataformaRepository plataformaRepository) {	
		this.plataformaRepository = plataformaRepository;
	}


	@GetMapping(value = "/save")
	public ResponseEntity<String> getSave() {
		
		System.out.println("TESTE");
		
//		plataformaRepository.save((Plataforma) plataforma);
		 
		return ResponseEntity.ok("Salvo com Sucesso");
	}
	
	
}
