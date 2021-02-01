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

import br.com.fattoria.sccm.api.EquipamentoApi;
import br.com.fattoria.sccm.api.MetodoAmostragemModel;
import br.com.fattoria.sccm.api.MetodoAmostragemModelAssembler;
import br.com.fattoria.sccm.api.MetodoAmostragemApi;
import br.com.fattoria.sccm.api.MetodoAmostragemModel;
import br.com.fattoria.sccm.persistence.model.MetodoAmostragem;
import br.com.fattoria.sccm.persistence.repository.MetodoAmostragemRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@Api(value = "metodoAmostragem")
@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class MetodoAmostragemController {

	private final Logger log = LoggerFactory.getLogger(MetodoAmostragemController.class);
	private MetodoAmostragemRepository metodoAmostragemRepository;
	
	public MetodoAmostragemController(MetodoAmostragemRepository metodoAmostragemRepository) {	
		this.metodoAmostragemRepository = metodoAmostragemRepository;
	}
	
	@PostMapping("/medotoAmostragem")
	@ApiOperation(value = "Add um Metodo de Amostragem")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Equipamento incluido com sucesso"),
    })
	ResponseEntity<MetodoAmostragemModel> create(@Valid @RequestBody MetodoAmostragemApi metodoAmostragem) throws URISyntaxException{
		
		log.info("criando metodo amostragem");
	
        MetodoAmostragem entity = metodoAmostragem.toEntity();
        
    	MetodoAmostragemModelAssembler assembler = new MetodoAmostragemModelAssembler(); 
    	MetodoAmostragemModel model = assembler.toModel(metodoAmostragemRepository.save(entity));
        
        final URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}").buildAndExpand(entity.getId()).toUri();
        
        return ResponseEntity.created(uri)
                .body(model);
	}
	
	@PutMapping("/metodoAmostragem/{id}")
	@ApiOperation(value = "Atualiza uma Amostragem")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Metodo Amostragem atualizado"),
    })
	ResponseEntity<MetodoAmostragemModel> update(@Valid @RequestBody MetodoAmostragemApi metodoAmostragem){
		
		log.info("Alterando Metodo Amostragem");
	
        MetodoAmostragem metodoAmostragemEntity = metodoAmostragem.toEntity();
        
    	MetodoAmostragemModelAssembler assembler = new MetodoAmostragemModelAssembler(); 
    	MetodoAmostragemModel model = assembler.toModel(metodoAmostragemRepository.save(metodoAmostragemEntity));
        
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(model);

	}
	
	@GetMapping("/metodoAmostragem")
    @ApiOperation(value = "Retorna uma lista de Metodo Amostragem")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de Metodo Amostragem"),
    })
	public ResponseEntity<CollectionModel<MetodoAmostragemModel>> getAll() {
    	
    	log.info("listando Metodo Amostragem");
    	 
    	Collection<MetodoAmostragem> lista = (Collection<MetodoAmostragem>) metodoAmostragemRepository.findAll();
    	
    	MetodoAmostragemModelAssembler assembler = new MetodoAmostragemModelAssembler(); 
    	CollectionModel<MetodoAmostragemModel> listPlataformaResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listPlataformaResource.add(new Link(uriString, "self"));
    	
	    return ResponseEntity.ok(listPlataformaResource);
	}
    
    @GetMapping("/metodoAmostragem/{id}")
    @ApiOperation(value = "Retorna um Metodo Amostragem")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma Metodo Amostragem"),
    })
	public ResponseEntity<MetodoAmostragemModel> getById(@PathVariable Long id) {
    	
    	log.info("Metodo Amostragem por id "+id);
    	 
    	Optional<MetodoAmostragem> pais = metodoAmostragemRepository.findById(id);
    	
    	MetodoAmostragemModelAssembler assembler = new MetodoAmostragemModelAssembler(); 
    	 
    	return pais.map(response -> ResponseEntity.ok().body(assembler.toModel(response)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
    
    @DeleteMapping("/metodoAmostragem/{id}")
    @ApiOperation(value = "Deleta um Amostragem")
    public ResponseEntity<?> delete(@PathVariable Long id) throws NotFoundException {
    	
    	 log.info("Metodo Amostragem por id "+id);
    	
    	 metodoAmostragemRepository.deleteById(id);
    	 Optional<MetodoAmostragem> pais = metodoAmostragemRepository.findById(id);
    	 
    	 return pais.map(p -> {metodoAmostragemRepository.deleteById(id); 
    	 return ResponseEntity.noContent().build();}).orElseThrow(() -> new NotFoundException("Método Amostragem não encontrado"));    	
    }

}
