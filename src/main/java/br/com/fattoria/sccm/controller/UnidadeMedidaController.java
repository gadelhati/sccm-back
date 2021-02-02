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

import br.com.fattoria.sccm.api.UnidadeMedidaApi;
import br.com.fattoria.sccm.api.UnidadeMedidaModel;
import br.com.fattoria.sccm.api.UnidadeMedidaModelAssembler;
import br.com.fattoria.sccm.persistence.model.UnidadeMedida;
import br.com.fattoria.sccm.persistence.repository.UnidadeMedidaRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@Api(value = "unidadeMedida")
@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class UnidadeMedidaController {
	
	private final Logger log = LoggerFactory.getLogger(UnidadeMedidaController.class);
	private UnidadeMedidaRepository unidadeMedidaRepository;
	
	public UnidadeMedidaController(UnidadeMedidaRepository unidadeMedidaRepository) {	
		this.unidadeMedidaRepository = unidadeMedidaRepository;
	}
	
	@PostMapping("/unidades_medida")
	@ApiOperation(value = "Add uma Unidade de Medida")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Unidade de Medida Criada"),
    })
	ResponseEntity<UnidadeMedidaModel> create(@Valid @RequestBody UnidadeMedidaApi unidadeMedida) throws URISyntaxException{
		
		log.info("criando Unidade Medida");
	
        UnidadeMedida unidadeMedidaEntity = unidadeMedida.toEntity();
        
    	UnidadeMedidaModelAssembler assembler = new UnidadeMedidaModelAssembler(); 
    	UnidadeMedidaModel UnidadeMedidaModel = assembler.toModel(unidadeMedidaRepository.save(unidadeMedidaEntity));
        
        final URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}").buildAndExpand(unidadeMedida.getId()).toUri();
        
        return ResponseEntity.created(uri).body(UnidadeMedidaModel);
	}
	
	@PutMapping("/unidades_medida/{id}")
	@ApiOperation(value = "Atualiza uma Unidade de Medida")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Unidade Medida Atualizada"),
    })
	ResponseEntity<UnidadeMedidaModel> update(@Valid @RequestBody UnidadeMedidaApi unidadeMedida){
		
		log.info("Alterando Unidade Medida");
	
        UnidadeMedida unidadeMedidaEntity = unidadeMedida.toEntity();
        
    	UnidadeMedidaModelAssembler assembler = new UnidadeMedidaModelAssembler(); 
    	UnidadeMedidaModel UnidadeMedidaModel = assembler.toModel(unidadeMedidaRepository.save(unidadeMedidaEntity));
        
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(UnidadeMedidaModel);

	}
	
	@GetMapping("/unidades_medida")
    @ApiOperation(value = "Retorna lista de Unidade Medidas")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a Unidade de Medidas"),
    })
	public ResponseEntity<CollectionModel<UnidadeMedidaModel>> getAll() {
    	
    	log.info("listando Area Técnicas");
    	 
    	Collection<UnidadeMedida> lista = (Collection<UnidadeMedida>) unidadeMedidaRepository.findAll();
    	
    	UnidadeMedidaModelAssembler assembler = new UnidadeMedidaModelAssembler(); 
    	CollectionModel<UnidadeMedidaModel> listResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listResource);
	}
    
    @GetMapping("/unidades_medida/{id}")
    @ApiOperation(value = "Retorna uma Unidade de Medida")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma Unidade Medida"),
    })
	public ResponseEntity<UnidadeMedidaModel> getById(@PathVariable Long id) {
    	
    	log.info("Unidade Medida por id "+id);
    	 
    	Optional<UnidadeMedida> areaTecnica = unidadeMedidaRepository.findById(id);
    	
    	UnidadeMedidaModelAssembler assembler = new UnidadeMedidaModelAssembler(); 
    	 
    	return areaTecnica.map(response -> ResponseEntity.ok().body(assembler.toModel(response))).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
    
    @DeleteMapping("/unidades_medida/{id}")
    @ApiOperation(value = "Deleta uma Area Técnica")
    public ResponseEntity<?> delete(@PathVariable Long id) throws NotFoundException{
    	
    	 log.info("Unidade de Medida por id "+id);
    	
    	 unidadeMedidaRepository.deleteById(id);
    	 Optional<UnidadeMedida> unidadeMedida = unidadeMedidaRepository.findById(id);
    	 
    	 return unidadeMedida.map(p -> {unidadeMedidaRepository.deleteById(id);
    	 return ResponseEntity.noContent().build();}).orElseThrow(() -> new NotFoundException("Unidade Medida não encontrada"));    	
    }
	


}
