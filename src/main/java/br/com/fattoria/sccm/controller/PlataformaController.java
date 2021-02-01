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
import org.springframework.hateoas.mediatype.hal.HalModelBuilder;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.TypedEntityLinks;
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

import br.com.fattoria.sccm.api.PlataformaApi;
import br.com.fattoria.sccm.api.PlataformaModel;
import br.com.fattoria.sccm.api.PlataformaModelAssembler;
import br.com.fattoria.sccm.persistence.model.Pais;
import br.com.fattoria.sccm.persistence.model.Plataforma;
import br.com.fattoria.sccm.persistence.model.TipoPlataforma;
import br.com.fattoria.sccm.persistence.repository.PaisRepository;
import br.com.fattoria.sccm.persistence.repository.PlataformaRepository;
import br.com.fattoria.sccm.persistence.repository.TipoPlataformaRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@Api(value = "Plataformas")
@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class PlataformaController {
	
	private final Logger log = LoggerFactory.getLogger(PlataformaController.class);
	private PlataformaRepository plataformaRepository;
	private TipoPlataformaRepository tipoPlataformaRepository;
	private PaisRepository paisRepository;
	
	private final TypedEntityLinks<PlataformaModel> links;
	
	public PlataformaController(PlataformaRepository plataformaRepository, TipoPlataformaRepository tipoPlataformaRepository, PaisRepository paisRepository, EntityLinks entityLinks) {
		this.plataformaRepository = plataformaRepository;
		this.tipoPlataformaRepository = tipoPlataformaRepository;
		this.paisRepository = paisRepository;
		this.links = entityLinks.forType(PlataformaModel::getId);
	}
	
	@PostMapping("/plataformas")
	@ApiOperation(value = "Cria uma plataforma")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Plataforma criada"),
    })
	ResponseEntity<PlataformaModel> create(@Valid @RequestBody PlataformaApi plataforma) throws URISyntaxException{
		
		log.info("criando plataforma");
	
        Optional<TipoPlataforma> tipoPlataforma = tipoPlataformaRepository.findById(plataforma.getIdTipoPlataforma());
        
        Optional<Pais> bandeira = paisRepository.findById(plataforma.getIdBandeira());
        
        Plataforma plataformaEntity = plataforma.toEntity();
        
        plataformaEntity.setTipoPlataforma(tipoPlataforma.get());
        
        plataformaEntity.setBandeira(bandeira.get());

    	PlataformaModelAssembler assembler = new PlataformaModelAssembler(); 
    	PlataformaModel plataformaModel = assembler.toModel(plataformaRepository.save(plataformaEntity));
 
        
        final URI uri =
                MvcUriComponentsBuilder.fromController(getClass())
                    .path("/{id}")
                    .buildAndExpand(plataformaEntity.getId())
                    .toUri();
        
        return ResponseEntity.created(uri)
                .body(plataformaModel);
	}
	
	@PutMapping("/plataformas/{id}")
	@ApiOperation(value = "Atualiza uma plataforma")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Plataforma atualizada"),
    })
	ResponseEntity<PlataformaModel> update(@Valid @RequestBody PlataformaApi plataforma){
		
		log.info("alterando plataforma");
	
        Optional<TipoPlataforma> tipoPlataforma = tipoPlataformaRepository.findById(plataforma.getIdTipoPlataforma());
        
        Optional<Pais> bandeira = paisRepository.findById(plataforma.getIdBandeira());
        
        Plataforma plataformaEntity = plataforma.toEntity();
        
        plataformaEntity.setTipoPlataforma(tipoPlataforma.get());
        
        plataformaEntity.setBandeira(bandeira.get());
        
    	PlataformaModelAssembler assembler = new PlataformaModelAssembler(); 
    	PlataformaModel plataformaModel = assembler.toModel(plataformaRepository.save(plataformaEntity));
        
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(plataformaModel);

	}
	
	@GetMapping("/plataformas")
    @ApiOperation(value = "Retorna uma lista de plataformas")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de plataformas"),
    })
	public ResponseEntity<CollectionModel<PlataformaModel>> getAll() {
    	
    	log.info("listando plataforma");
    	 
    	Collection<Plataforma> lista = (Collection<Plataforma>) plataformaRepository.findAll();
    	
    	PlataformaModelAssembler assembler = new PlataformaModelAssembler(); 
    	CollectionModel<PlataformaModel> listPlataformaResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listPlataformaResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listPlataformaResource);
	}
    
    @GetMapping("/plataformas/{id}")
    @ApiOperation(value = "Retorna uma plataforma")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma plataforma"),
    })
	public ResponseEntity<PlataformaModel> getById(@PathVariable Long id) {
    	
    	log.info("plataforma por id "+id);
    	 
    	Optional<Plataforma> plataforma = plataformaRepository.findById(id);
    	
    	PlataformaModelAssembler assembler = new PlataformaModelAssembler(); 
    	 
    	return plataforma.map(response -> ResponseEntity.ok().body(assembler.toModel(response)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
    
    @DeleteMapping("/plataformas/{id}")
    @ApiOperation(value = "Deleta uma plataforma")
    public ResponseEntity<?> delete(@PathVariable Long id) throws NotFoundException{
    	
    	 log.info("plataforma por id "+id);
    	
    	 plataformaRepository.deleteById(id);
    	 Optional<Plataforma> plataforma = plataformaRepository.findById(id);
    	 
    	 return plataforma.map(
    	            p -> {
    	            	plataformaRepository.deleteById(id);
    	              return ResponseEntity.noContent().build();
    	            }).orElseThrow(() -> new NotFoundException("Plataforma não encontrada"));
    	
    }
    
	@PutMapping("/plataformas/active/{id}")
	@ApiOperation(value = "Atualiza uma plataforma")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Plataforma atualizada"),
    })
	ResponseEntity<PlataformaModel> active(@Valid @PathVariable Long id){
		
		log.info("alterando plataforma");
		
		Optional<Plataforma> plataforma = plataformaRepository.findById(id);
		
		PlataformaModelAssembler assembler = new PlataformaModelAssembler(); 
		
    	return plataforma.map(response -> {
    		response.setAtivo(!response.isAtivo());
    		plataformaRepository.save(response);
    		return ResponseEntity.ok().body(assembler.toModel(response));
    	}).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

	}

}
