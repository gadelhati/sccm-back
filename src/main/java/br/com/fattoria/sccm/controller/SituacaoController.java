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

import br.com.fattoria.sccm.api.SituacaoApi;
import br.com.fattoria.sccm.api.SituacaoModel;
import br.com.fattoria.sccm.api.SituacaoModelAssembler;
import br.com.fattoria.sccm.persistence.model.Situacao;
import br.com.fattoria.sccm.persistence.repository.SituacaoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@Api(value = "situacoes")
@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class SituacaoController {

	private final Logger log = LoggerFactory.getLogger(SituacaoController.class);
	private SituacaoRepository situacaoRepository;
	
	public SituacaoController(SituacaoRepository situacaoRepository) {
		this.situacaoRepository = situacaoRepository;
	}
	
	@PostMapping("/situacoes")
	@ApiOperation(value = "Cria uma Situacao")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Situacao criada"),
    })
	ResponseEntity<SituacaoModel> create(@Valid @RequestBody SituacaoApi api) throws URISyntaxException{
		
        Situacao entity = api.toEntity();
        
    	SituacaoModelAssembler assembler = new SituacaoModelAssembler(); 
    	SituacaoModel paisModel = assembler.toModel(situacaoRepository.save(entity));
 
        
        final URI uri =
                MvcUriComponentsBuilder.fromController(getClass())
                    .path("/{id}")
                    .buildAndExpand(entity.getId())
                    .toUri();
        
        return ResponseEntity.created(uri)
                .body(paisModel);
	}
	
	@PutMapping("/situacoes/{id}")
	@ApiOperation(value = "Atualiza uma situacao")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Situacao atualizada"),
    })
	ResponseEntity<SituacaoModel> update(@Valid @RequestBody SituacaoApi situacao){
		
        Situacao situacaoEntity = situacao.toEntity();
        
    	SituacaoModelAssembler assembler = new SituacaoModelAssembler(); 
    	SituacaoModel situacaoModel = assembler.toModel(situacaoRepository.save(situacaoEntity));
        
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(situacaoModel);

	}
	
	@GetMapping("/situacoes")
    @ApiOperation(value = "Retorna uma lista de situacoes")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Situacaos"),
    })
	public ResponseEntity<CollectionModel<SituacaoModel>> getAll() {
    	
    	Collection<Situacao> lista = (Collection<Situacao>) situacaoRepository.findAll();
    	
    	SituacaoModelAssembler assembler = new SituacaoModelAssembler(); 
    	CollectionModel<SituacaoModel> listPlataformaResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listPlataformaResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listPlataformaResource);
	}
    
    @GetMapping("/situacoes/{id}")
    @ApiOperation(value = "Retorna uma Situacao")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma situacao"),
    })
	public ResponseEntity<SituacaoModel> getById(@PathVariable Long id) {
    	 
    	Optional<Situacao> situacao = situacaoRepository.findById(id);
    	
    	SituacaoModelAssembler assembler = new SituacaoModelAssembler(); 
    	 
    	return situacao.map(response -> ResponseEntity.ok().body(assembler.toModel(response)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
    
    @DeleteMapping("/situacoes/{id}")
    @ApiOperation(value = "Deleta uma situacao")
    public ResponseEntity<?> delete(@PathVariable Long id) throws NotFoundException{
    	
    	 Optional<Situacao> situacao = situacaoRepository.findById(id);
    	 
    	 return situacao.map(
    	            p -> {
    	            	situacaoRepository.deleteById(id);
    	              return ResponseEntity.noContent().build();
    	            }).orElseThrow(() -> new NotFoundException("Situacao não encontrado"));
    	
    }
	
}
