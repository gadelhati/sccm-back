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

import br.com.fattoria.sccm.api.DestinoApi;
import br.com.fattoria.sccm.api.DestinoModel;
import br.com.fattoria.sccm.api.DestinoModelAssembler;
import br.com.fattoria.sccm.persistence.model.Destino;
import br.com.fattoria.sccm.persistence.repository.DestinoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@Api(value = "destino")
@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class DestinoController {

	private final Logger log = LoggerFactory.getLogger(DestinoController.class);
	private DestinoRepository destinoRepository;
	
	public DestinoController(DestinoRepository destinoRepository) {
		this.destinoRepository = destinoRepository;
	}
	
	@PostMapping("/destino")
	@ApiOperation(value = "Cria um Destino")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Destino criado"),
    })
	ResponseEntity<DestinoModel> create(@Valid @RequestBody DestinoApi api) throws URISyntaxException{
		
        Destino entity = api.toEntity();
        
    	DestinoModelAssembler assembler = new DestinoModelAssembler(); 
    	DestinoModel paisModel = assembler.toModel(destinoRepository.save(entity));
 
        
        final URI uri =
                MvcUriComponentsBuilder.fromController(getClass())
                    .path("/{id}")
                    .buildAndExpand(entity.getId())
                    .toUri();
        
        return ResponseEntity.created(uri)
                .body(paisModel);
	}
	
	@PutMapping("/destino/{id}")
	@ApiOperation(value = "Atualiza um destino")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Destino atualizado"),
    })
	ResponseEntity<DestinoModel> update(@Valid @RequestBody DestinoApi pais){
		
        Destino paisEntity = pais.toEntity();
        
    	DestinoModelAssembler assembler = new DestinoModelAssembler(); 
    	DestinoModel paisModel = assembler.toModel(destinoRepository.save(paisEntity));
        
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(paisModel);

	}
	
	@GetMapping("/destino")
    @ApiOperation(value = "Retorna uma lista de destinos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Destinos"),
    })
	public ResponseEntity<CollectionModel<DestinoModel>> getAll() {
    	
    	Collection<Destino> lista = (Collection<Destino>) destinoRepository.findAll();
    	
    	DestinoModelAssembler assembler = new DestinoModelAssembler(); 
    	CollectionModel<DestinoModel> listPlataformaResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listPlataformaResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listPlataformaResource);
	}
    
    @GetMapping("/destino/{id}")
    @ApiOperation(value = "Retorna um Destino")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma destino"),
    })
	public ResponseEntity<DestinoModel> getById(@PathVariable Long id) {
    	 
    	Optional<Destino> destino = destinoRepository.findById(id);
    	
    	DestinoModelAssembler assembler = new DestinoModelAssembler(); 
    	 
    	return destino.map(response -> ResponseEntity.ok().body(assembler.toModel(response)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
    
    @DeleteMapping("/destino/{id}")
    @ApiOperation(value = "Deleta um destino")
    public ResponseEntity<?> delete(@PathVariable Long id) throws NotFoundException{
    	
    	 Optional<Destino> destino = destinoRepository.findById(id);
    	 
    	 return destino.map(
    	            p -> {
    	            	destinoRepository.deleteById(id);
    	              return ResponseEntity.noContent().build();
    	            }).orElseThrow(() -> new NotFoundException("Destino não encontrado"));
    	
    }
	
}
