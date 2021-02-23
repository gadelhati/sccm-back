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

import br.com.fattoria.sccm.api.TipoEnderecoApi;
import br.com.fattoria.sccm.api.TipoEnderecoModel;
import br.com.fattoria.sccm.api.TipoEnderecoModelAssembler;
import br.com.fattoria.sccm.persistence.model.TipoEndereco;
import br.com.fattoria.sccm.persistence.repository.TipoEnderecoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@Api(value = "tipoEndereco")
@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class TipoEnderecoController {
	
	private final Logger log = LoggerFactory.getLogger(TipoEnderecoController.class);
	private TipoEnderecoRepository tipoEnderecoRepository;
	
	public TipoEnderecoController(TipoEnderecoRepository tipoEnderecoRepository) {
		this.tipoEnderecoRepository = tipoEnderecoRepository;
	}
	
	@PostMapping("/tipoEndereco")
	@ApiOperation(value = "Cria um pais")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Tipo Endereco criado"),
    })
	ResponseEntity<TipoEnderecoModel> create(@Valid @RequestBody TipoEnderecoApi tipoEndereco) throws URISyntaxException {
		
        TipoEndereco tipoEnderecoEntity = tipoEndereco.toEntity();
        
    	TipoEnderecoModelAssembler assembler = new TipoEnderecoModelAssembler(); 
    	TipoEnderecoModel tipoEnderecoModel = assembler.toModel(tipoEnderecoRepository.save(tipoEnderecoEntity));
 
        
        final URI uri =
                MvcUriComponentsBuilder.fromController(getClass())
                    .path("/{id}")
                    .buildAndExpand(tipoEnderecoEntity.getId())
                    .toUri();
        
        return ResponseEntity.created(uri)
                .body(tipoEnderecoModel);
	}
	
	@PutMapping("/tipoEndereco/{id}")
	@ApiOperation(value = "Atualiza um Tipo Endereco")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Tipo Endereco atualizado"),
    })
	ResponseEntity<TipoEnderecoModel> update(@Valid @RequestBody TipoEnderecoApi tipoEndereco) {
	
        TipoEndereco entity = tipoEndereco.toEntity();
        
    	TipoEnderecoModelAssembler assembler = new TipoEnderecoModelAssembler(); 
    	TipoEnderecoModel model = assembler.toModel(tipoEnderecoRepository.save(entity));
        
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(model);

	}
	
	@GetMapping("/tipoEndereco")
    @ApiOperation(value = "Retorna uma lista tipo enderecos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de tipo enderecos"),
    })
	public ResponseEntity<CollectionModel<TipoEnderecoModel>> getAll() {
    	 
    	Collection<TipoEndereco> lista = (Collection<TipoEndereco>) tipoEnderecoRepository.findAll();
    	
    	TipoEnderecoModelAssembler assembler = new TipoEnderecoModelAssembler(); 
    	CollectionModel<TipoEnderecoModel> listPlataformaResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listPlataformaResource.add(new Link(uriString, "self"));
    	
	    return ResponseEntity.ok(listPlataformaResource);
	}
    
    @GetMapping("/tipoEndereco/{id}")
    @ApiOperation(value = "Retorna um tipoEndereco")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma pais"),
    })
	public ResponseEntity<TipoEnderecoModel> getById(@PathVariable Long id) {
    	
    	Optional<TipoEndereco> opt = tipoEnderecoRepository.findById(id);
    	
    	TipoEnderecoModelAssembler assembler = new TipoEnderecoModelAssembler(); 
    	 
    	return opt.map(response -> ResponseEntity.ok().body(assembler.toModel(response)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
    
    @DeleteMapping("/tipoEndereco/{id}")
    @ApiOperation(value = "Deleta um Tipo Endereco")
    public ResponseEntity<?> delete(@PathVariable Long id) throws NotFoundException{
    	
    	 Optional<TipoEndereco> opt = tipoEnderecoRepository.findById(id);
    	 
    	 return opt.map(
    	            p -> {
    	            	tipoEnderecoRepository.deleteById(id);
    	              return ResponseEntity.noContent().build();
    	            }).orElseThrow(() -> new NotFoundException("Tipo Endereco não encontrado"));
    	
    }


}
