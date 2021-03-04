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

import br.com.fattoria.sccm.api.ShipSynopApi;
import br.com.fattoria.sccm.api.ShipSynopModel;
import br.com.fattoria.sccm.api.ShipSynopModelAssembler;
import br.com.fattoria.sccm.persistence.model.ShipSynop;
import br.com.fattoria.sccm.persistence.repository.ShipSynopRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@Api(value = "shipSynop")
@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class ShipSynopController {

	private final Logger log = LoggerFactory.getLogger(ShipSynopController.class);
	private ShipSynopRepository shipSynopRepository;
	
	public ShipSynopController(ShipSynopRepository shipSynopRepository) {
		this.shipSynopRepository = shipSynopRepository;
	}
	
	@PostMapping("/shipSynop")
	@ApiOperation(value = "Cria um Destino")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Destino criado"),
    })
	ResponseEntity<ShipSynopModel> create(@Valid @RequestBody ShipSynopApi api) throws URISyntaxException{
		
        ShipSynop entity = api.toEntity();
        
    	ShipSynopModelAssembler assembler = new ShipSynopModelAssembler(); 
    	ShipSynopModel model = assembler.toModel(shipSynopRepository.save(entity));
 
        
        final URI uri =
                MvcUriComponentsBuilder.fromController(getClass())
                    .path("/{id}")
                    .buildAndExpand(entity.getId())
                    .toUri();
        
        return ResponseEntity.created(uri)
                .body(model);
	}
	
	@PutMapping("/shipSynop/{id}")
	@ApiOperation(value = "Atualizar")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Atualizado com Sucesso!"),
    })
	ResponseEntity<ShipSynopModel> update(@Valid @RequestBody ShipSynopApi api){
		
        ShipSynop entity = api.toEntity();
        
    	ShipSynopModelAssembler assembler = new ShipSynopModelAssembler(); 
    	ShipSynopModel paisModel = assembler.toModel(shipSynopRepository.save(entity));
        
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(paisModel);

	}
	
	@GetMapping("/shipSynop")
    @ApiOperation(value = "Todos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Sucesso"),
    })
	public ResponseEntity<CollectionModel<ShipSynopModel>> getAll() {
    	
    	Collection<ShipSynop> lista = (Collection<ShipSynop>) shipSynopRepository.findAll();
    	
    	ShipSynopModelAssembler assembler = new ShipSynopModelAssembler(); 
    	CollectionModel<ShipSynopModel> listPlataformaResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listPlataformaResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listPlataformaResource);
	}
    
    @GetMapping("/shipSynop/{id}")
    @ApiOperation(value = "Retorna um objeto")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornou um objeto"),
    })
	public ResponseEntity<ShipSynopModel> getById(@PathVariable Long id) {
    	 
    	Optional<ShipSynop> destino = shipSynopRepository.findById(id);
    	
    	ShipSynopModelAssembler assembler = new ShipSynopModelAssembler(); 
    	 
    	return destino.map(response -> ResponseEntity.ok().body(assembler.toModel(response)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
    
    @DeleteMapping("/shipSynop/{id}")
    @ApiOperation(value = "Deleta")
    public ResponseEntity<?> delete(@PathVariable Long id) throws NotFoundException {
    	
    	 Optional<ShipSynop> object = shipSynopRepository.findById(id);
    	 
    	 return object.map(
    	            p -> {
    	            	shipSynopRepository.deleteById(id);
    	              return ResponseEntity.noContent().build();
    	            }).orElseThrow(() -> new NotFoundException("Não encontrado"));
    	
    }
}
