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

import br.com.fattoria.sccm.api.TipoPlataformaApi;
import br.com.fattoria.sccm.api.TipoPlataformaModel;
import br.com.fattoria.sccm.api.TipoPlataformaModelAssembler;
import br.com.fattoria.sccm.persistence.model.TipoPlataforma;
import br.com.fattoria.sccm.persistence.repository.TipoPlataformaRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@Api(value = "TipoPlataformas")
@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class TipoPlataformaController {
	
	private final Logger log = LoggerFactory.getLogger(TipoPlataformaController.class);
	private TipoPlataformaRepository tipoPlataformaRepository;
	
	public TipoPlataformaController(TipoPlataformaRepository tipoPlataformaRepository) {
		this.tipoPlataformaRepository = tipoPlataformaRepository;
	}
	
	@PostMapping("/tipos_plataforma")
	@ApiOperation(value = "Cria um tipo plataforma")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Tipo Plataforma criada"),
    })
	ResponseEntity<TipoPlataformaModel> create(@Valid @RequestBody TipoPlataformaApi tipoPlataforma) throws URISyntaxException{
		
		log.info("criando tipoPlataforma");
	
        TipoPlataforma tipoPlataformaEntity = tipoPlataforma.toEntity();
        
    	TipoPlataformaModelAssembler assembler = new TipoPlataformaModelAssembler(); 
    	TipoPlataformaModel tipoPlataformaModel = assembler.toModel(tipoPlataformaRepository.save(tipoPlataformaEntity));
 
        
        final URI uri =
                MvcUriComponentsBuilder.fromController(getClass())
                    .path("/{id}")
                    .buildAndExpand(tipoPlataformaEntity.getId())
                    .toUri();
        
        return ResponseEntity.created(uri)
                .body(tipoPlataformaModel);
	}
	
	@PutMapping("/tipos_plataforma/{id}")
	@ApiOperation(value = "Atualiza um tipo plataforma")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Tipo plataforma atualizada"),
    })
	ResponseEntity<TipoPlataformaModel> update(@Valid @RequestBody TipoPlataformaApi tipoPlataforma){
		
		log.info("alterando tipoPlataforma");
	
        TipoPlataforma tipoPlataformaEntity = tipoPlataforma.toEntity();
        
    	TipoPlataformaModelAssembler assembler = new TipoPlataformaModelAssembler(); 
    	TipoPlataformaModel tipoPlataformaModel = assembler.toModel(tipoPlataformaRepository.save(tipoPlataformaEntity));
        
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(tipoPlataformaModel);

	}
	
	@GetMapping("/tipos_plataforma")
    @ApiOperation(value = "Retorna uma lista de tipo plataformas")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de tipo plataformas"),
    })
	public ResponseEntity<CollectionModel<TipoPlataformaModel>> getAll() {
    	
    	log.info("listando tipoPlataforma");
    	 
    	Collection<TipoPlataforma> lista = (Collection<TipoPlataforma>) tipoPlataformaRepository.findAll();
    	
    	TipoPlataformaModelAssembler assembler = new TipoPlataformaModelAssembler(); 
    	CollectionModel<TipoPlataformaModel> listPlataformaResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listPlataformaResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listPlataformaResource);
	}
    
    @GetMapping("/tipos_plataforma/{id}")
    @ApiOperation(value = "Retorna um tipo plataforma")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma tipoPlataforma"),
    })
	public ResponseEntity<TipoPlataformaModel> getById(@PathVariable Long id) {
    	
    	log.info("plataforma por id "+id);
    	 
    	Optional<TipoPlataforma> tipoPlataforma = tipoPlataformaRepository.findById(id);
    	
    	TipoPlataformaModelAssembler assembler = new TipoPlataformaModelAssembler(); 
    	 
    	return tipoPlataforma.map(response -> ResponseEntity.ok().body(assembler.toModel(response)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
    
    @DeleteMapping("/tipos_plataforma/{id}")
    @ApiOperation(value = "Deleta um tipo plataforma")
    public ResponseEntity<?> delete(@PathVariable Long id) throws NotFoundException{
    	
    	 log.info("plataforma por id "+id);
    	
    	 Optional<TipoPlataforma> tipoPlataforma = tipoPlataformaRepository.findById(id);
    	 
    	 return tipoPlataforma.map(
    	            p -> {
    	            	tipoPlataformaRepository.deleteById(id);
    	              return ResponseEntity.noContent().build();
    	            }).orElseThrow(() -> new NotFoundException("Plataforma não encontrada"));
    	
    }

}
