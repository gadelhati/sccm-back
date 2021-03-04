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

import br.com.fattoria.sccm.api.ControleInternoApi;
import br.com.fattoria.sccm.api.ControleInternoModel;
import br.com.fattoria.sccm.api.ControleInternoModelAssembler;
import br.com.fattoria.sccm.persistence.model.ControleInterno;
import br.com.fattoria.sccm.persistence.repository.ControleInternoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@Api(value = "controleInterno")
@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class ControleInternoController {

	private final Logger log = LoggerFactory.getLogger(ControleInternoController.class);
	private ControleInternoRepository controleInternoRepository;
	
	public ControleInternoController(ControleInternoRepository controleInternoRepository) {
		this.controleInternoRepository = controleInternoRepository;
	}
	
	@PostMapping("/controleInterno")
	@ApiOperation(value = "Cria um Destino")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Destino criado"),
    })
	ResponseEntity<ControleInternoModel> create(@Valid @RequestBody ControleInternoApi api) throws URISyntaxException{
		
        ControleInterno entity = api.toEntity();
        
    	ControleInternoModelAssembler assembler = new ControleInternoModelAssembler(); 
    	ControleInternoModel model = assembler.toModel(controleInternoRepository.save(entity));
 
        
        final URI uri =
                MvcUriComponentsBuilder.fromController(getClass())
                    .path("/{id}")
                    .buildAndExpand(entity.getId())
                    .toUri();
        
        return ResponseEntity.created(uri)
                .body(model);
	}
	
	@PutMapping("/controleInterno/{id}")
	@ApiOperation(value = "Atualizar")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Atualizado com Sucesso!"),
    })
	ResponseEntity<ControleInternoModel> update(@Valid @RequestBody ControleInternoApi api){
		
        ControleInterno entity = api.toEntity();
        
    	ControleInternoModelAssembler assembler = new ControleInternoModelAssembler(); 
    	ControleInternoModel model = assembler.toModel(controleInternoRepository.save(entity));
        
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(model);

	}
	
	@GetMapping("/controleInterno")
    @ApiOperation(value = "Todos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Sucesso"),
    })
	public ResponseEntity<CollectionModel<ControleInternoModel>> getAll() {
    	
    	Collection<ControleInterno> lista = (Collection<ControleInterno>) controleInternoRepository.findAll();
    	
    	ControleInternoModelAssembler assembler = new ControleInternoModelAssembler(); 
    	CollectionModel<ControleInternoModel> listPlataformaResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listPlataformaResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listPlataformaResource);
	}
    
    @GetMapping("/controleInterno/{id}")
    @ApiOperation(value = "Retorna um objeto")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornou um objeto"),
    })
	public ResponseEntity<ControleInternoModel> getById(@PathVariable Long id) {
    	 
    	Optional<ControleInterno> object = controleInternoRepository.findById(id);
    	
    	ControleInternoModelAssembler assembler = new ControleInternoModelAssembler(); 
    	 
    	return object.map(response -> ResponseEntity.ok().body(assembler.toModel(response)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
    
    @DeleteMapping("/controleInterno/{id}")
    @ApiOperation(value = "Deleta")
    public ResponseEntity<?> delete(@PathVariable Long id) throws NotFoundException {
    	
    	 Optional<ControleInterno> object = controleInternoRepository.findById(id);
    	 
    	 return object.map(
    	            p -> {
    	            	controleInternoRepository.deleteById(id);
    	              return ResponseEntity.noContent().build();
    	            }).orElseThrow(() -> new NotFoundException("Não encontrado"));
    	
    }
}
