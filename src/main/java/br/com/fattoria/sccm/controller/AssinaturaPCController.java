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

import br.com.fattoria.sccm.api.AssinaturaPCApi;
import br.com.fattoria.sccm.api.AssinaturaPCModel;
import br.com.fattoria.sccm.api.AssinaturaPCModelAssembler;
import br.com.fattoria.sccm.persistence.model.AssinaturaPC;
import br.com.fattoria.sccm.persistence.repository.AssinaturaPCRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@Api(value = "assinatura")
@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class AssinaturaPCController {

	private final Logger log = LoggerFactory.getLogger(AssinaturaPCController.class);
	private AssinaturaPCRepository assinaturaPCRepository;
	
	public AssinaturaPCController(AssinaturaPCRepository assinaturaPCRepository) {
		this.assinaturaPCRepository = assinaturaPCRepository;
	}
	
	@PostMapping("/assinatura")
	@ApiOperation(value = "Cria um assinatura")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "assinatura criado"),
    })
	ResponseEntity<AssinaturaPCModel> create(@Valid @RequestBody AssinaturaPCApi api) throws URISyntaxException{
		
        AssinaturaPC entity = api.toEntity();
        
    	AssinaturaPCModelAssembler assembler = new AssinaturaPCModelAssembler(); 
    	AssinaturaPCModel model = assembler.toModel(assinaturaPCRepository.save(entity));
        
        final URI uri =
                MvcUriComponentsBuilder.fromController(getClass())
                    .path("/{id}")
                    .buildAndExpand(entity.getId())
                    .toUri();
        
        return ResponseEntity.created(uri)
                .body(model);
	}
	
	@PutMapping("/assinatura/{id}")
	@ApiOperation(value = "Atualiza um assinatura")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Destino atualizado"),
    })
	ResponseEntity<AssinaturaPCModel> update(@Valid @RequestBody AssinaturaPCApi pais){
		
        AssinaturaPC paisEntity = pais.toEntity();
        
    	AssinaturaPCModelAssembler assembler = new AssinaturaPCModelAssembler(); 
    	AssinaturaPCModel paisModel = assembler.toModel(assinaturaPCRepository.save(paisEntity));
        
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(paisModel);

	}
	
	@GetMapping("/assinatura")
    @ApiOperation(value = "Retorna uma lista de assinaturas")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Destinos"),
    })
	public ResponseEntity<CollectionModel<AssinaturaPCModel>> getAll() {
    	
    	Collection<AssinaturaPC> lista = (Collection<AssinaturaPC>) assinaturaPCRepository.findAll();
    	
    	AssinaturaPCModelAssembler assembler = new AssinaturaPCModelAssembler(); 
    	CollectionModel<AssinaturaPCModel> listPlataformaResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listPlataformaResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listPlataformaResource);
	}
    
    @GetMapping("/assinatura/{id}")
    @ApiOperation(value = "Retorna uma assinatura")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma destino"),
    })
	public ResponseEntity<AssinaturaPCModel> getById(@PathVariable Long id) {
    	 
    	Optional<AssinaturaPC> destino = assinaturaPCRepository.findById(id);
    	
    	AssinaturaPCModelAssembler assembler = new AssinaturaPCModelAssembler(); 
    	 
    	return destino.map(response -> ResponseEntity.ok().body(assembler.toModel(response)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
    
    @DeleteMapping("/assinatura/{id}")
    @ApiOperation(value = "Deleta um assinatura")
    public ResponseEntity<?> delete(@PathVariable Long id) throws NotFoundException{
    	
    	 Optional<AssinaturaPC> destino = assinaturaPCRepository.findById(id);
    	 
    	 return destino.map(
    	            p -> {
    	            	assinaturaPCRepository.deleteById(id);
    	              return ResponseEntity.noContent().build();
    	            }).orElseThrow(() -> new NotFoundException("Assinatura não encontrado"));
    	
    }
	
}
