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

import br.com.fattoria.sccm.api.TipoAnexoApi;
import br.com.fattoria.sccm.api.TipoAnexoModel;
import br.com.fattoria.sccm.api.TipoAnexoModelAssembler;
import br.com.fattoria.sccm.persistence.model.TipoAnexo;
import br.com.fattoria.sccm.persistence.repository.TipoAnexoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@Api(value = "Tipo Anexo")
@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class TipoAnexoController {
	
	private final Logger log = LoggerFactory.getLogger(DestinoController.class);
	private TipoAnexoRepository tipoAnexoRepository;
	
	public TipoAnexoController(TipoAnexoRepository tipoAnexoRepository) {
		this.tipoAnexoRepository = tipoAnexoRepository;
	}
	
	@PostMapping("/tipos_anexo")
	@ApiOperation(value = "Cria um Tipo Anexo")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Tipo Anexo criado"),
    })
	ResponseEntity<TipoAnexoModel> create(@Valid @RequestBody TipoAnexoApi api) throws URISyntaxException {
		
        TipoAnexo entity = api.toEntity();
        
    	TipoAnexoModelAssembler assembler = new TipoAnexoModelAssembler(); 
    	TipoAnexoModel paisModel = assembler.toModel(tipoAnexoRepository.save(entity));
 
        
        final URI uri =
                MvcUriComponentsBuilder.fromController(getClass())
                    .path("/{id}")
                    .buildAndExpand(entity.getId())
                    .toUri();
        
        return ResponseEntity.created(uri)
                .body(paisModel);
	}
	
	@PutMapping("/tipos_anexo/{id}")
	@ApiOperation(value = "Atualiza um Tipo Anexo")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Tipo Anexo atualizado"),
    })
	ResponseEntity<TipoAnexoModel> update(@Valid @RequestBody TipoAnexoApi tipoAnexo){
		
        TipoAnexo entity = tipoAnexo.toEntity();
        
    	TipoAnexoModelAssembler assembler = new TipoAnexoModelAssembler(); 
    	TipoAnexoModel paisModel = assembler.toModel(tipoAnexoRepository.save(entity));
        
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(paisModel);

	}
	
	@GetMapping("/tipos_anexo")
    @ApiOperation(value = "Retorna uma lista de tipos de anexos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Anexos"),
    })
	public ResponseEntity<CollectionModel<TipoAnexoModel>> getAll() {
		
		log.info("listando tipos anexos");
    	
    	Collection<TipoAnexo> lista = (Collection<TipoAnexo>) tipoAnexoRepository.findAll();
    	
    	TipoAnexoModelAssembler assembler = new TipoAnexoModelAssembler(); 
    	CollectionModel<TipoAnexoModel> listPlataformaResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listPlataformaResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listPlataformaResource);
	}
    
    @GetMapping("/tipos_anexo/{id}")
    @ApiOperation(value = "Retorna um tipo de anexo")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma anexo"),
    })
	public ResponseEntity<TipoAnexoModel> getById(@PathVariable Long id) {
    	
    	log.info("tipo anexo por id");
    	 
    	Optional<TipoAnexo> pais = tipoAnexoRepository.findById(id);
    	
    	TipoAnexoModelAssembler assembler = new TipoAnexoModelAssembler(); 
    	 
    	return pais.map(response -> ResponseEntity.ok().body(assembler.toModel(response)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
    
    @DeleteMapping("/tipos_anexo/{id}")
    @ApiOperation(value = "Deleta um destino")
    public ResponseEntity<?> delete(@PathVariable Long id) throws NotFoundException{
    	
    	 Optional<TipoAnexo> tipoAnexo = tipoAnexoRepository.findById(id);
    	 
    	 return tipoAnexo.map(
    	            p -> {
    	            	tipoAnexoRepository.deleteById(id);
    	              return ResponseEntity.noContent().build();
    	            }).orElseThrow(() -> new NotFoundException("Tipo Anexo não encontrado"));
    	
    }
	


}
