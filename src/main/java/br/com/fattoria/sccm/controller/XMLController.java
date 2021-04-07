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

import br.com.fattoria.sccm.api.XMLApi;
import br.com.fattoria.sccm.api.XMLModel;
import br.com.fattoria.sccm.api.XMLModelAssembler;
import br.com.fattoria.sccm.api.ShipSynopModel;
import br.com.fattoria.sccm.persistence.model.XML;
import br.com.fattoria.sccm.persistence.model.Instituicao;
import br.com.fattoria.sccm.persistence.model.PesquisaCientifica;
import br.com.fattoria.sccm.persistence.repository.XMLRepository;
import br.com.fattoria.sccm.persistence.repository.InstituicaoRepository;
import br.com.fattoria.sccm.persistence.repository.PesquisaCientificaRepository;
import br.com.fattoria.sccm.persistence.repository.XMLRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;


public class XMLController {

	private final Logger log = LoggerFactory.getLogger(ControleInternoController.class);
	private XMLRepository xmlRepository;
	
	public XMLController(XMLRepository xmlRepository) {
		this.xmlRepository = xmlRepository;
	}
	
	@PostMapping("/xml")
	@ApiOperation(value = "Criação")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Criado"),
    })
	ResponseEntity<XMLModel> create(@Valid @RequestBody XMLApi api) throws URISyntaxException {
		
        XML entity = api.toEntity();
                
    	XMLModelAssembler assembler = new XMLModelAssembler(); 
    	XMLModel model = assembler.toModel(xmlRepository.save(entity));
        
        final URI uri =
                MvcUriComponentsBuilder.fromController(getClass())
                    .path("/{id}")
                    .buildAndExpand(entity.getId())
                    .toUri();
        
        return ResponseEntity.created(uri)
                .body(model);
	}
	
	@PutMapping("/xml/{id}")
	@ApiOperation(value = "Atualizar")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Atualizado com Sucesso!"),
    })
	ResponseEntity<XMLModel> update(@Valid @RequestBody XMLApi api){
		
		XML entity = api.toEntity();
        
    	XMLModelAssembler assembler = new XMLModelAssembler(); 
    	XMLModel model = assembler.toModel(xmlRepository.save(entity));
        
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(model);
	}
	
	@GetMapping("/xml")
    @ApiOperation(value = "Todos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Sucesso"),
    })
	public ResponseEntity<CollectionModel<XMLModel>> getAll() {
    	
    	Collection<XML> lista = (Collection<XML>) xmlRepository.findAll();
    	
    	XMLModelAssembler assembler = new XMLModelAssembler(); 
    	CollectionModel<XMLModel> listResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listResource);
	}
    
    @GetMapping("/xml/{id}")
    @ApiOperation(value = "Retorna um objeto")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornou um objeto"),
    })
	public ResponseEntity<XMLModel> getById(@PathVariable Long id) {
    	 
    	Optional<XML> object = xmlRepository.findById(id);
    	
    	XMLModelAssembler assembler = new XMLModelAssembler(); 
    	 
    	return object.map(response -> ResponseEntity.ok().body(assembler.toModel(response)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
    
    @DeleteMapping("/xml/{id}")
    @ApiOperation(value = "Deleta")
    public ResponseEntity<?> delete(@PathVariable Long id) throws NotFoundException {
    	
    	 Optional<XML> object = xmlRepository.findById(id);
    	 
    	 return object.map(
    	            p -> {
    	            	xmlRepository.deleteById(id);
    	              return ResponseEntity.noContent().build();
    	            }).orElseThrow(() -> new NotFoundException("Não encontrado"));
    	
    }
}
