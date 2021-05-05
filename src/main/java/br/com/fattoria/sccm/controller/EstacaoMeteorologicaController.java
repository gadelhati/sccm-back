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

import br.com.fattoria.sccm.api.EstacaoMeteorologicaApi;
import br.com.fattoria.sccm.api.EstacaoMeteorologicaModel;
import br.com.fattoria.sccm.api.EstacaoMeteorologicaModelAssembler;
import br.com.fattoria.sccm.persistence.model.EstacaoMeteorologica;
import br.com.fattoria.sccm.persistence.repository.EstacaoMeteorologicaRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@Api(value = "estacoes_meteorologicas")
@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class EstacaoMeteorologicaController {

	private final Logger log = LoggerFactory.getLogger(EstacaoMeteorologicaController.class);
	private EstacaoMeteorologicaRepository estacaoMeteorologicaRepository;
	
	public EstacaoMeteorologicaController(EstacaoMeteorologicaRepository estacaoMeteorologicaRepository) {
		this.estacaoMeteorologicaRepository = estacaoMeteorologicaRepository;
	}
	
	@PostMapping("/estacoes_meteorologicas")
	@ApiOperation(value = "Cria uma Estacao Meteorologica")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Estacao Meteorologica criada"),
    })
	ResponseEntity<EstacaoMeteorologicaModel> create(@Valid @RequestBody EstacaoMeteorologicaApi api) throws URISyntaxException{
		
        EstacaoMeteorologica entity = api.toEntity();
        
    	EstacaoMeteorologicaModelAssembler assembler = new EstacaoMeteorologicaModelAssembler(); 
    	EstacaoMeteorologicaModel paisModel = assembler.toModel(estacaoMeteorologicaRepository.save(entity));
 
        
        final URI uri =
                MvcUriComponentsBuilder.fromController(getClass())
                    .path("/{id}")
                    .buildAndExpand(entity.getId())
                    .toUri();
        
        return ResponseEntity.created(uri)
                .body(paisModel);
	}
	
	@PutMapping("/estacoes_meteorologicas/{id}")
	@ApiOperation(value = "Atualiza uma situacao")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Estacao Meteorologica atualizada"),
    })
	ResponseEntity<EstacaoMeteorologicaModel> update(@Valid @RequestBody EstacaoMeteorologicaApi pais){
		
        EstacaoMeteorologica paisEntity = pais.toEntity();
        
    	EstacaoMeteorologicaModelAssembler assembler = new EstacaoMeteorologicaModelAssembler(); 
    	EstacaoMeteorologicaModel paisModel = assembler.toModel(estacaoMeteorologicaRepository.save(paisEntity));
        
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(paisModel);

	}
	
	@GetMapping("/estacoes_meteorologicas")
    @ApiOperation(value = "Retorna uma lista de Estacao")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Estacao Meteorologicas"),
    })
	public ResponseEntity<CollectionModel<EstacaoMeteorologicaModel>> getAll() {
    	
    	Collection<EstacaoMeteorologica> lista = (Collection<EstacaoMeteorologica>) estacaoMeteorologicaRepository.findAll();
    	
    	EstacaoMeteorologicaModelAssembler assembler = new EstacaoMeteorologicaModelAssembler(); 
    	CollectionModel<EstacaoMeteorologicaModel> listPlataformaResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listPlataformaResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listPlataformaResource);
	}
    
    @GetMapping("/estacoes_meteorologicas/{id}")
    @ApiOperation(value = "Retorna uma Estacao Meteorologica")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma estação"),
    })
	public ResponseEntity<EstacaoMeteorologicaModel> getById(@PathVariable Long id) {
    	 
    	Optional<EstacaoMeteorologica> situacao = estacaoMeteorologicaRepository.findById(id);
    	
    	EstacaoMeteorologicaModelAssembler assembler = new EstacaoMeteorologicaModelAssembler(); 
    	 
    	return situacao.map(response -> ResponseEntity.ok().body(assembler.toModel(response)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
    
    @DeleteMapping("/estacoes_meteorologicas/{id}")
    @ApiOperation(value = "Deleta uma situacao")
    public ResponseEntity<?> delete(@PathVariable Long id) throws NotFoundException{
    	
    	 Optional<EstacaoMeteorologica> situacao = estacaoMeteorologicaRepository.findById(id);
    	 
    	 return situacao.map(
    	            p -> {
    	            	estacaoMeteorologicaRepository.deleteById(id);
    	              return ResponseEntity.noContent().build();
    	            }).orElseThrow(() -> new NotFoundException("Estacao Meteorologica não encontrado"));
    	
    }
	
}
