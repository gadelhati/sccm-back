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

import br.com.fattoria.sccm.api.ComissaoApi;
import br.com.fattoria.sccm.api.ComissaoModel;
import br.com.fattoria.sccm.api.ComissaoModelAssembler;
import br.com.fattoria.sccm.persistence.model.Comissao;
import br.com.fattoria.sccm.persistence.repository.ComissaoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@Api(value = "comissao")
@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class ComissaoController {

	private final Logger log = LoggerFactory.getLogger(ComissaoController.class);
	private ComissaoRepository comissaoRepository;
	
	public ComissaoController(ComissaoRepository comissaoRepository) {
		this.comissaoRepository = comissaoRepository;
	}
	
	@PostMapping("/comissoes")
	@ApiOperation(value = "Cria uma Comissão")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Comissão criada"),
    })
	ResponseEntity<ComissaoModel> create(@Valid @RequestBody ComissaoApi api) throws URISyntaxException {
		
        Comissao entity = api.toEntity();
        
    	ComissaoModelAssembler assembler = new ComissaoModelAssembler(); 
    	ComissaoModel model = assembler.toModel(comissaoRepository.save(entity));
 
        
        final URI uri =
                MvcUriComponentsBuilder.fromController(getClass())
                    .path("/{id}")
                    .buildAndExpand(entity.getId())
                    .toUri();
        
        return ResponseEntity.created(uri)
                .body(model);
	}
	
	@PutMapping("/comissoes/{id}")
	@ApiOperation(value = "Atualiza uma Comissão")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Comissão atualizada"),
    })
	ResponseEntity<ComissaoModel> update(@Valid @RequestBody ComissaoApi api){
		
        Comissao entity = api.toEntity();
        
    	ComissaoModelAssembler assembler = new ComissaoModelAssembler(); 
    	ComissaoModel paisModel = assembler.toModel(comissaoRepository.save(entity));
        
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(paisModel);

	}
	
	@GetMapping("/comissoes")
    @ApiOperation(value = "Retorna uma lista de comissões")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Comissões"),
    })
	public ResponseEntity<CollectionModel<ComissaoModel>> getAll() {
    	
    	Collection<Comissao> lista = (Collection<Comissao>) comissaoRepository.findAll();
    	
    	ComissaoModelAssembler assembler = new ComissaoModelAssembler(); 
    	CollectionModel<ComissaoModel> listResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listResource);
	}
    
    @GetMapping("/comissoes/{id}")
    @ApiOperation(value = "Retorna uma Comissão")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma comissão"),
    })
	public ResponseEntity<ComissaoModel> getById(@PathVariable Long id) {
    	 
    	Optional<Comissao> entity = comissaoRepository.findById(id);
    	
    	ComissaoModelAssembler assembler = new ComissaoModelAssembler(); 
    	 
    	return entity.map(response -> ResponseEntity.ok().body(assembler.toModel(response)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
    
    @DeleteMapping("/comissoes/{id}")
    @ApiOperation(value = "Deleta uma comissão")
    public ResponseEntity<?> delete(@PathVariable Long id) throws NotFoundException{

    	 Optional<Comissao> entity = comissaoRepository.findById(id);
    	 
    	 return entity.map(
    	            p -> {
    	            	comissaoRepository.deleteById(id);
    	              return ResponseEntity.noContent().build();
    	            }).orElseThrow(() -> new NotFoundException("Comissão não encontrado"));
    	
    }
	
}
