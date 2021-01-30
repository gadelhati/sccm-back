package br.com.fattoria.sccm.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fattoria.sccm.api.PaisApi;
import br.com.fattoria.sccm.api.PaisModel;
import br.com.fattoria.sccm.api.PaisModelAssembler;
import br.com.fattoria.sccm.persistence.model.Pais;
import br.com.fattoria.sccm.persistence.repository.PaisRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@Api(value = "Paises")
@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class PaisController {
	
	private final Logger log = LoggerFactory.getLogger(PaisController.class);
	private PaisRepository paisRepository;
	
	public PaisController(PaisRepository paisRepository) {
		this.paisRepository = paisRepository;
	}
	
	@PostMapping("/paises")
	@ApiOperation(value = "Cria um pais")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Tipo Plataforma criada"),
    })
	ResponseEntity<PaisModel> create(@Valid @RequestBody PaisApi pais) throws URISyntaxException{
		
		log.info("criando pais");
	
        Pais paisEntity = pais.toEntity();
        
    	PaisModelAssembler assembler = new PaisModelAssembler(); 
    	PaisModel paisModel = assembler.toModel(paisRepository.save(paisEntity));
 
        
        final URI uri =
                MvcUriComponentsBuilder.fromController(getClass())
                    .path("/{id}")
                    .buildAndExpand(paisEntity.getId())
                    .toUri();
        
        return ResponseEntity.created(uri)
                .body(paisModel);
	}
	
	@PutMapping("/paises/{id}")
	@ApiOperation(value = "Atualiza um pais")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Tipo plataforma atualizada"),
    })
	ResponseEntity<PaisModel> update(@Valid @RequestBody PaisApi pais){
		
		log.info("alterando pais");
	
        Pais paisEntity = pais.toEntity();
        
    	PaisModelAssembler assembler = new PaisModelAssembler(); 
    	PaisModel paisModel = assembler.toModel(paisRepository.save(paisEntity));
        
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(paisModel);

	}
	
	@GetMapping("/paises")
    @ApiOperation(value = "Retorna uma lista de paises")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de paises"),
    })
	public ResponseEntity<CollectionModel<PaisModel>> getAll() {
    	
    	log.info("listando pais");
    	 
    	Collection<Pais> lista = (Collection<Pais>) paisRepository.findAll();
    	
    	PaisModelAssembler assembler = new PaisModelAssembler(); 
    	CollectionModel<PaisModel> listPlataformaResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listPlataformaResource.add(new Link(uriString, "self"));
    	
	    return ResponseEntity.ok(listPlataformaResource);
	}
    
    @GetMapping("/paises/{id}")
    @ApiOperation(value = "Retorna um pais")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma pais"),
    })
	public ResponseEntity<PaisModel> getById(@PathVariable Long id) {
    	
    	log.info("plataforma por id "+id);
    	 
    	Optional<Pais> pais = paisRepository.findById(id);
    	
    	PaisModelAssembler assembler = new PaisModelAssembler(); 
    	 
    	return pais.map(response -> ResponseEntity.ok().body(assembler.toModel(response)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
    
    @DeleteMapping("/paises/{id}")
    @ApiOperation(value = "Deleta um pais")
    public ResponseEntity<?> delete(@PathVariable Long id) throws NotFoundException{
    	
    	 log.info("plataforma por id "+id);
    	
    	 paisRepository.deleteById(id);
    	 Optional<Pais> pais = paisRepository.findById(id);
    	 
    	 return pais.map(
    	            p -> {
    	            	paisRepository.deleteById(id);
    	              return ResponseEntity.noContent().build();
    	            }).orElseThrow(() -> new NotFoundException("Plataforma não encontrada"));
    	
    }

}
