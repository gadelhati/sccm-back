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

import br.com.fattoria.sccm.api.PalavraChaveApi;
import br.com.fattoria.sccm.api.PalavraChaveModel;
import br.com.fattoria.sccm.api.PalavraChaveModelAssembler;
import br.com.fattoria.sccm.persistence.model.PalavraChave;
import br.com.fattoria.sccm.persistence.repository.PalavraChaveRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@Api(value = "palavraChave")
@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class PalavraChaveController {

	private final Logger log = LoggerFactory.getLogger(PalavraChaveController.class);
	private PalavraChaveRepository palavraChaveRepository;
	
	public PalavraChaveController(PalavraChaveRepository palavraChaveRepository) {
		this.palavraChaveRepository = palavraChaveRepository;
	}
	
	@PostMapping("/palavraChave")
	@ApiOperation(value = "Criação")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Criado com Sucesso!"),
    })
	ResponseEntity<PalavraChaveModel> create(@Valid @RequestBody PalavraChaveApi api) throws URISyntaxException {
		
        PalavraChave entity = api.toEntity();
        
    	PalavraChaveModelAssembler assembler = new PalavraChaveModelAssembler(); 
    	PalavraChaveModel model = assembler.toModel(palavraChaveRepository.save(entity));
 
        
        final URI uri =
                MvcUriComponentsBuilder.fromController(getClass())
                    .path("/{id}")
                    .buildAndExpand(entity.getId())
                    .toUri();
        
        return ResponseEntity.created(uri)
                .body(model);
	}
	
	@PutMapping("/palavraChave/{id}")
	@ApiOperation(value = "Atualizar")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Atualizado com Sucesso!"),
    })
	ResponseEntity<PalavraChaveModel> update(@Valid @RequestBody PalavraChaveApi api){
		
        PalavraChave entity = api.toEntity();
        
    	PalavraChaveModelAssembler assembler = new PalavraChaveModelAssembler(); 
    	PalavraChaveModel model = assembler.toModel(palavraChaveRepository.save(entity));
        
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(model);

	}
	
	@GetMapping("/palavraChave")
    @ApiOperation(value = "Retornar")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Sucesso"),
    })
	public ResponseEntity<CollectionModel<PalavraChaveModel>> getAll() {
    	
    	Collection<PalavraChave> lista = (Collection<PalavraChave>) palavraChaveRepository.findAll();
    	
    	PalavraChaveModelAssembler assembler = new PalavraChaveModelAssembler(); 
    	CollectionModel<PalavraChaveModel> listResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listResource);
	}
    
    @GetMapping("/palavraChave/{id}")
    @ApiOperation(value = "Retornar")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna um resultado"),
    })
	public ResponseEntity<PalavraChaveModel> getById(@PathVariable Long id) {
    	 
    	Optional<PalavraChave> entity = palavraChaveRepository.findById(id);
    	
    	PalavraChaveModelAssembler assembler = new PalavraChaveModelAssembler(); 
    	 
    	return entity.map(response -> ResponseEntity.ok().body(assembler.toModel(response)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
    
    @DeleteMapping("/palavraChave/{id}")
    @ApiOperation(value = "Deleta um item")
    public ResponseEntity<?> delete(@PathVariable Long id) throws NotFoundException{
    	
    	 Optional<PalavraChave> entity = palavraChaveRepository.findById(id);
    	 
    	 return entity.map(
    	            p -> {
    	            	palavraChaveRepository.deleteById(id);
    	              return ResponseEntity.noContent().build();
    	            }).orElseThrow(() -> new NotFoundException("Resultado não encontrado"));    	
    }
	
}
