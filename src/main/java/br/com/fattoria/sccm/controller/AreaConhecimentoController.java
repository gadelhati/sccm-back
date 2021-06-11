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

import br.com.fattoria.sccm.api.AreaConhecimentoApi;
import br.com.fattoria.sccm.api.AreaConhecimentoModel;
import br.com.fattoria.sccm.api.AreaConhecimentoModelAssembler;
import br.com.fattoria.sccm.persistence.model.AreaConhecimento;
import br.com.fattoria.sccm.persistence.repository.AreaConhecimentoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@Api(value = "areaConhecimento")
@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class AreaConhecimentoController {
	
	private final Logger log = LoggerFactory.getLogger(PlataformaController.class);
	private AreaConhecimentoRepository areaConhecimentoRepository;
	
	public AreaConhecimentoController(AreaConhecimentoRepository areaConhecimentoRepository) {
		this.areaConhecimentoRepository = areaConhecimentoRepository;		
	}
	
	@PostMapping("/areas_conhecimento")
	@ApiOperation(value = "Cria uma Area de Conhecimento")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Area Conhecimento criada"),
    })
	ResponseEntity<AreaConhecimentoModel> create(@Valid @RequestBody AreaConhecimentoApi areaConhecimento) throws URISyntaxException{
		
		log.info("criando area de conhecimento");
	
		AreaConhecimento areaConhecimentoEntity = areaConhecimento.toEntity();
        
    	AreaConhecimentoModelAssembler assembler = new AreaConhecimentoModelAssembler(); 
    	AreaConhecimentoModel paisModel = assembler.toModel(areaConhecimentoRepository.save(areaConhecimentoEntity));
 
        
        final URI uri = 
                MvcUriComponentsBuilder.fromController(getClass())
                    .path("/{id}")
                    .buildAndExpand(areaConhecimentoEntity.getId())
                    .toUri();
        
        return ResponseEntity.created(uri)
                .body(paisModel);
	}
	
	@PutMapping("/areas_conhecimento/{id}")
	@ApiOperation(value = "Atualiza um pais")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Area Conhecimento atualizada"),
    })
	ResponseEntity<AreaConhecimentoModel> update(@Valid @RequestBody AreaConhecimentoApi areaConrecimentoApi){
		
        AreaConhecimento areaConhecimentoEntity = areaConrecimentoApi.toEntity();
        
    	AreaConhecimentoModelAssembler assembler = new AreaConhecimentoModelAssembler(); 
    	AreaConhecimentoModel areaConhecimentoModel = assembler.toModel(areaConhecimentoRepository.save(areaConhecimentoEntity));
        
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(areaConhecimentoModel);

	}
	
	@GetMapping("/areas_conhecimento")
    @ApiOperation(value = "Retorna uma lista de Area de Conhecimento")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de Area de Conhecimento"),
    })
	public ResponseEntity<CollectionModel<AreaConhecimentoModel>> getAll() {
    	 
    	Collection<AreaConhecimento> lista = (Collection<AreaConhecimento>) areaConhecimentoRepository.findAll();
    	
    	AreaConhecimentoModelAssembler assembler = new AreaConhecimentoModelAssembler(); 
    	CollectionModel<AreaConhecimentoModel> listAreasResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listAreasResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listAreasResource);
	}
    
    @GetMapping("/areas_conhecimento/{id}")
    @ApiOperation(value = "Retorna um areaConhecimento")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma areaConhecimento"),
    })
	public ResponseEntity<AreaConhecimentoModel> getById(@PathVariable Long id) {
    	
    	log.info("plataforma por id "+id);
    	 
    	Optional<AreaConhecimento> areaConhecimento = areaConhecimentoRepository.findById(id);
    	
    	AreaConhecimentoModelAssembler assembler = new AreaConhecimentoModelAssembler(); 
    	 
    	return areaConhecimento.map(response -> ResponseEntity.ok().body(assembler.toModel(response)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
    
    @DeleteMapping("/areas_conhecimento/{id}")
    @ApiOperation(value = "Deleta um areaConhecimento")
    public ResponseEntity<?> delete(@PathVariable Long id) throws NotFoundException{
    	
    	 log.info("plataforma por id "+id);
    	
    	 areaConhecimentoRepository.deleteById(id);
    	 Optional<AreaConhecimento> areaConhecimento = areaConhecimentoRepository.findById(id);
    	 
    	 return areaConhecimento.map(
    	            p -> {
    	            	areaConhecimentoRepository.deleteById(id);
    	              return ResponseEntity.noContent().build();
    	            }).orElseThrow(() -> new NotFoundException("Área de conhecimento não encontrada"));
    	
    }
    
	@GetMapping("/areas_conhecimento/ativas")
    @ApiOperation(value = "Retorna uma lista de Area de Conhecimento ativas")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de Area de Conhecimento"),
    })
	public ResponseEntity<CollectionModel<AreaConhecimentoModel>> getAllAtivas() {
    	 
    	Collection<AreaConhecimento> lista = (Collection<AreaConhecimento>) areaConhecimentoRepository.findAllByAtivoTrue();
    	
    	AreaConhecimentoModelAssembler assembler = new AreaConhecimentoModelAssembler(); 
    	CollectionModel<AreaConhecimentoModel> listPlataformaResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listPlataformaResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listPlataformaResource);
	}

}
