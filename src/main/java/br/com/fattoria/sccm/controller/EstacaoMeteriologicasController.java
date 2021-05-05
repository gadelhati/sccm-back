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

import br.com.fattoria.sccm.api.EstacaoMeteriologicaApi;
import br.com.fattoria.sccm.api.EstacaoMeteriologicaModel;
import br.com.fattoria.sccm.api.EstacaoMeteriologicaModelAssembler;
import br.com.fattoria.sccm.persistence.model.EstacaoMeteriologica;
import br.com.fattoria.sccm.persistence.repository.EstacaoMeteriologicaRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@Api(value = "estacoes_meteriologicas")
@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class EstacaoMeteriologicasController {

	private final Logger log = LoggerFactory.getLogger(EstacaoMeteriologicasController.class);
	private EstacaoMeteriologicaRepository estacaoMeteriologicaRepository;
	
	public EstacaoMeteriologicasController(EstacaoMeteriologicaRepository estacaoMeteriologicaRepository) {
		this.estacaoMeteriologicaRepository = estacaoMeteriologicaRepository;
	}
	
	@PostMapping("/estacoes_meteriologicas")
	@ApiOperation(value = "Cria uma Estacao Meteriologica")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Estacao Meteriologica criada"),
    })
	ResponseEntity<EstacaoMeteriologicaModel> create(@Valid @RequestBody EstacaoMeteriologicaApi api) throws URISyntaxException{
		
        EstacaoMeteriologica entity = api.toEntity();
        
    	EstacaoMeteriologicaModelAssembler assembler = new EstacaoMeteriologicaModelAssembler(); 
    	EstacaoMeteriologicaModel paisModel = assembler.toModel(estacaoMeteriologicaRepository.save(entity));
 
        
        final URI uri =
                MvcUriComponentsBuilder.fromController(getClass())
                    .path("/{id}")
                    .buildAndExpand(entity.getId())
                    .toUri();
        
        return ResponseEntity.created(uri)
                .body(paisModel);
	}
	
	@PutMapping("/estacoes_meteriologicas/{id}")
	@ApiOperation(value = "Atualiza uma situacao")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Estacao Meteriologica atualizada"),
    })
	ResponseEntity<EstacaoMeteriologicaModel> update(@Valid @RequestBody EstacaoMeteriologicaApi pais){
		
        EstacaoMeteriologica paisEntity = pais.toEntity();
        
    	EstacaoMeteriologicaModelAssembler assembler = new EstacaoMeteriologicaModelAssembler(); 
    	EstacaoMeteriologicaModel paisModel = assembler.toModel(estacaoMeteriologicaRepository.save(paisEntity));
        
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(paisModel);

	}
	
	@GetMapping("/estacoes_meteriologicas")
    @ApiOperation(value = "Retorna uma lista de Estacao")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Estacao Meteriologicas"),
    })
	public ResponseEntity<CollectionModel<EstacaoMeteriologicaModel>> getAll() {
    	
    	Collection<EstacaoMeteriologica> lista = (Collection<EstacaoMeteriologica>) estacaoMeteriologicaRepository.findAll();
    	
    	EstacaoMeteriologicaModelAssembler assembler = new EstacaoMeteriologicaModelAssembler(); 
    	CollectionModel<EstacaoMeteriologicaModel> listPlataformaResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listPlataformaResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listPlataformaResource);
	}
    
    @GetMapping("/estacoes_meteriologicas/{id}")
    @ApiOperation(value = "Retorna uma Estacao Meteriologica")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma estação"),
    })
	public ResponseEntity<EstacaoMeteriologicaModel> getById(@PathVariable Long id) {
    	 
    	Optional<EstacaoMeteriologica> estacao = estacaoMeteriologicaRepository.findById(id);
    	
    	EstacaoMeteriologicaModelAssembler assembler = new EstacaoMeteriologicaModelAssembler(); 
    	 
    	return estacao.map(response -> ResponseEntity.ok().body(assembler.toModel(response)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
    
    @DeleteMapping("/estacoes_meteriologicas/{id}")
    @ApiOperation(value = "Deleta uma situacao")
    public ResponseEntity<?> delete(@PathVariable Long id) throws NotFoundException{
    	
    	 Optional<EstacaoMeteriologica> estacao = estacaoMeteriologicaRepository.findById(id);
    	 
    	 return estacao.map(
    	            p -> {
    	            	estacaoMeteriologicaRepository.deleteById(id);
    	              return ResponseEntity.noContent().build();
    	            }).orElseThrow(() -> new NotFoundException("Estacao Meteriologica não encontrado"));
    	
    }
	
}
