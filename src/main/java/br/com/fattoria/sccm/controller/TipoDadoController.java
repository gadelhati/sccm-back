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

import br.com.fattoria.sccm.api.TipoDadoApi;
import br.com.fattoria.sccm.api.TipoDadoModel;
import br.com.fattoria.sccm.api.TipoDadoModelAssembler;
import br.com.fattoria.sccm.persistence.model.TipoDado;
import br.com.fattoria.sccm.persistence.model.UnidadeMedida;
import br.com.fattoria.sccm.persistence.repository.TipoDadoRepository;
import br.com.fattoria.sccm.persistence.repository.UnidadeMedidaRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@Api(value = "Tipo Dado")
@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class TipoDadoController {
	
	private final Logger log = LoggerFactory.getLogger(TipoDadoController.class);
	private TipoDadoRepository tipoDadoRepository;
	private UnidadeMedidaRepository unidadeMedidaRepository;
	
	public TipoDadoController(TipoDadoRepository tipoDadoRepository,
			UnidadeMedidaRepository unidadeMedidaRepository) {
		this.tipoDadoRepository = tipoDadoRepository;
		this.unidadeMedidaRepository = unidadeMedidaRepository;
	}
	
	@PostMapping("/tipos_dado")
	@ApiOperation(value = "Add um TipoDado")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "TipoDado incluido com sucesso"),
    })
	ResponseEntity<TipoDadoModel> create(@Valid @RequestBody TipoDadoApi tipoDado) throws URISyntaxException{
		
		log.info("criando quipamento");
	
        TipoDado tipoDadoEntity = tipoDado.toEntity();
    	
    	if(tipoDado.getIdUnidadeMedida() != null) {
    		Optional<UnidadeMedida> unidadeMedida = unidadeMedidaRepository.findById(tipoDado.getIdUnidadeMedida());
    		tipoDadoEntity.setUnidadeMedida(unidadeMedida.get());
    	}
    	
    	tipoDadoEntity = tipoDadoRepository.save(tipoDadoEntity);
    	
    	TipoDadoModelAssembler assembler = new TipoDadoModelAssembler(); 
    	TipoDadoModel paisModel = assembler.toModel(tipoDadoEntity);
        
        final URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}").buildAndExpand(tipoDadoEntity.getId()).toUri();
        
        return ResponseEntity.created(uri)
                .body(paisModel);
	}
	
	@PutMapping("/tipos_dado/{id}")
	@ApiOperation(value = "Atualiza um TipoDado")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "TipoDado atualizado"),
    })
	ResponseEntity<TipoDadoModel> update(@Valid @RequestBody TipoDadoApi tipoDado){
		
		log.info("alterando tipoDado");
	
		TipoDado tipoDadoEntity = tipoDado.toEntity();
        
    	if(tipoDado.getIdUnidadeMedida() != null) {
    		Optional<UnidadeMedida> unidadeMedida = unidadeMedidaRepository.findById(tipoDado.getIdUnidadeMedida());
    		tipoDadoEntity.setUnidadeMedida(unidadeMedida.get());
    	}
    	
    	tipoDadoEntity = tipoDadoRepository.save(tipoDadoEntity);
    	
    	TipoDadoModelAssembler assembler = new TipoDadoModelAssembler(); 
    	TipoDadoModel tipoDadoModel = assembler.toModel(tipoDadoRepository.save(tipoDadoEntity));
        
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(tipoDadoModel);

	}
	
	@GetMapping("/tipos_dado")
    @ApiOperation(value = "Retorna uma lista de tipoDado")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de tipoDado"),
    })
	public ResponseEntity<CollectionModel<TipoDadoModel>> getAll() {
    	
    	log.info("listando tipoDado");
    	 
    	Collection<TipoDado> lista = (Collection<TipoDado>) tipoDadoRepository.findAll();
    	
    	TipoDadoModelAssembler assembler = new TipoDadoModelAssembler(); 
    	CollectionModel<TipoDadoModel> listTipoDadoResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listTipoDadoResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listTipoDadoResource);
	}
    
    @GetMapping("/tipos_dado/{id}")
    @ApiOperation(value = "Retorna um tipoDado")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma tipoDado"),
    })
	public ResponseEntity<TipoDadoModel> getById(@PathVariable Long id) {
    	
    	log.info("TipoDado por id "+id);
    	 
    	Optional<TipoDado> tipoDado = tipoDadoRepository.findById(id);
    	
    	TipoDadoModelAssembler assembler = new TipoDadoModelAssembler(); 
    	 
    	return tipoDado.map(response -> ResponseEntity.ok().body(assembler.toModel(response)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
    
    @DeleteMapping("/tipos_dado/{id}")
    @ApiOperation(value = "Deleta um tipo_dado")
    public ResponseEntity<?> delete(@PathVariable Long id) throws NotFoundException{
    	
    	 log.info("TipoDado por id "+id);
    	
    	 tipoDadoRepository.deleteById(id);
    	 Optional<TipoDado> pais = tipoDadoRepository.findById(id);
    	 
    	 return pais.map(p -> {tipoDadoRepository.deleteById(id); 
    	 return ResponseEntity.noContent().build();}).orElseThrow(() -> new NotFoundException("TipoDado não encontrada"));    	
    }
    
	@PutMapping("/tipos_dado/active/{id}")
	@ApiOperation(value = "Ativa/Desativa um tipo_dado")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Tipo dado atualizada"),
    })
	ResponseEntity<TipoDadoModel> active(@Valid @PathVariable Long id){
		
		log.info("alterando tipo dado");
		
		Optional<TipoDado> tipoDado = tipoDadoRepository.findById(id);
		
		TipoDadoModelAssembler assembler = new TipoDadoModelAssembler(); 
		
    	return tipoDado.map(response -> {
    		response.setAtivo(!response.isAtivo());
    		tipoDadoRepository.save(response);
    		return ResponseEntity.ok().body(assembler.toModel(response));
    	}).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

	}

}
