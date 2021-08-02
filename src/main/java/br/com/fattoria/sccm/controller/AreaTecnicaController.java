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

import br.com.fattoria.sccm.api.AreaTecnicaApi;
import br.com.fattoria.sccm.api.AreaTecnicaModel;
import br.com.fattoria.sccm.api.AreaTecnicaModelAssembler;
import br.com.fattoria.sccm.persistence.model.AreaTecnica;
import br.com.fattoria.sccm.persistence.repository.AreaTecnicaRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@Api(value = "AreaTecnica")
@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class AreaTecnicaController {
	
	private final Logger log = LoggerFactory.getLogger(AreaTecnicaController.class);
	private AreaTecnicaRepository areaTecnicaRepository;
		
	public AreaTecnicaController(AreaTecnicaRepository areaTecnicaRepository) {
		this.areaTecnicaRepository = areaTecnicaRepository;
	}
	
	@PostMapping("/areas_tecnica")
	@ApiOperation(value = "Add uma área técnica")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Área Técnica criada"),
    })
	ResponseEntity<AreaTecnicaModel> create(@Valid @RequestBody AreaTecnicaApi areaTecnica) throws URISyntaxException{
		
		log.info("criando area tecnica");
	
        AreaTecnica areaTecnicaEntity = areaTecnica.toEntity();
        
    	AreaTecnicaModelAssembler assembler = new AreaTecnicaModelAssembler(); 
    	AreaTecnicaModel areaTecnicaModel = assembler.toModel(areaTecnicaRepository.save(areaTecnicaEntity));
        
        final URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}").buildAndExpand(areaTecnicaEntity.getId()).toUri();        
        return ResponseEntity.created(uri).body(areaTecnicaModel);
	}
	
	@PutMapping("/areas_tecnica/{id}")
	@ApiOperation(value = "Atualiza uma area tecnica")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Área Técnica Atualizada"),
    })
	ResponseEntity<AreaTecnicaModel> update(@Valid @RequestBody AreaTecnicaApi areaTecnica){
		
		log.info("alterando Area Técnica");
	
        AreaTecnica areaTecnicaEntity = areaTecnica.toEntity();
        
    	AreaTecnicaModelAssembler assembler = new AreaTecnicaModelAssembler(); 
    	AreaTecnicaModel AreaTecnicaModel = assembler.toModel(areaTecnicaRepository.save(areaTecnicaEntity));
        
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(AreaTecnicaModel);

	}
	
	@GetMapping("/areas_tecnica")
    @ApiOperation(value = "Retorna lista de Area Técnicas")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de Area Técnicas"),
    })
	public ResponseEntity<CollectionModel<AreaTecnicaModel>> getAll() {
    	
    	log.info("listando Area Técnicas");
    	 
    	Collection<AreaTecnica> lista = (Collection<AreaTecnica>) areaTecnicaRepository.findAll();
    	
    	AreaTecnicaModelAssembler assembler = new AreaTecnicaModelAssembler(); 
    	CollectionModel<AreaTecnicaModel> listPlataformaResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listPlataformaResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listPlataformaResource);
	}
    
    @GetMapping("/areas_tecnica/{id}")
    @ApiOperation(value = "Retorna um Area Tecnica")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma Area Tecnica"),
    })
	public ResponseEntity<AreaTecnicaModel> getById(@PathVariable Long id) {
    	
    	log.info("Area Técnica por id "+id);
    	 
    	Optional<AreaTecnica> areaTecnica = areaTecnicaRepository.findById(id);
    	
    	AreaTecnicaModelAssembler assembler = new AreaTecnicaModelAssembler(); 
    	 
    	return areaTecnica.map(response -> ResponseEntity.ok().body(assembler.toModel(response))).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
    
    @DeleteMapping("/areas_tecnica/{id}")
    @ApiOperation(value = "Deleta uma Area Técnica")
    public ResponseEntity<?> delete(@PathVariable Long id) throws NotFoundException{
    	
    	 log.info("plataforma por id "+id);
    	
    	 Optional<AreaTecnica> areaTecnica = areaTecnicaRepository.findById(id);
    	 
    	 return areaTecnica.map(p -> {areaTecnicaRepository.deleteById(id);
    	 return ResponseEntity.noContent().build();}).orElseThrow(() -> new NotFoundException("Area Técnica não encontrada"));    	
    }
    
	@GetMapping("/areas_tecnica/ativas")
    @ApiOperation(value = "Retorna lista de Area Técnicas")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de Area Técnicas"),
    })
	public ResponseEntity<CollectionModel<AreaTecnicaModel>> getAllAtivas() {
    	
    	log.info("listando Area Técnicas");
    	 
    	Collection<AreaTecnica> lista = (Collection<AreaTecnica>) areaTecnicaRepository.findAllByAtivoTrue();
    	
    	AreaTecnicaModelAssembler assembler = new AreaTecnicaModelAssembler(); 
    	CollectionModel<AreaTecnicaModel> listPlataformaResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listPlataformaResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listPlataformaResource);
	}
	
}