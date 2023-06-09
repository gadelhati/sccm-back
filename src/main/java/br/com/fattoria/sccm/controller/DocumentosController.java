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

import br.com.fattoria.sccm.api.DocumentosApi;
import br.com.fattoria.sccm.api.DocumentosModel;
import br.com.fattoria.sccm.api.DocumentosModelAssembler;
import br.com.fattoria.sccm.persistence.model.ControleInterno;
import br.com.fattoria.sccm.persistence.model.Destino;
import br.com.fattoria.sccm.persistence.model.Documento;
import br.com.fattoria.sccm.persistence.model.PesquisaCientifica;
import br.com.fattoria.sccm.persistence.model.TipoAnexo;
import br.com.fattoria.sccm.persistence.repository.ControleInternoRepository;
import br.com.fattoria.sccm.persistence.repository.DestinoRepository;
import br.com.fattoria.sccm.persistence.repository.DocumentosRepository;
import br.com.fattoria.sccm.persistence.repository.PesquisaCientificaRepository;
import br.com.fattoria.sccm.persistence.repository.TipoAnexoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@Api(value = "documento")
@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class DocumentosController {

	private final Logger log = LoggerFactory.getLogger(DocumentosController.class);
	private final DocumentosRepository documentosRepository;
	private final TipoAnexoRepository tipoAnexoRepository;
	private final ControleInternoRepository controleInternoRepository;
	private final DestinoRepository destinoRepository;
	
	public DocumentosController(DocumentosRepository documentosRepository, TipoAnexoRepository tipoAnexoRepository,
			ControleInternoRepository controleInternoRepository, DestinoRepository destinoRepository) {
		super();
		this.documentosRepository = documentosRepository;
		this.tipoAnexoRepository = tipoAnexoRepository;
		this.controleInternoRepository = controleInternoRepository;
		this.destinoRepository = destinoRepository;
	}

	@PostMapping("/documentos")
	@ApiOperation(value = "Cria um Documentos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Documentos criado"),
    })
	ResponseEntity<DocumentosModel> create(@Valid @RequestBody DocumentosApi api) throws URISyntaxException{
		
		log.info("Salvando documento");
		
        Optional<TipoAnexo> tipoAnexo = tipoAnexoRepository.findById(api.getIdTipoAnexo());
		
        Documento entity = api.toEntity();
        
        if(api.getIdControleInterno() != null) {
        	Optional<ControleInterno> controleInterno = controleInternoRepository.findById(api.getIdControleInterno());
        	entity.setControleInterno(controleInterno.get());
        }
        
        if(api.getIdDestino() != null) {
        	Optional<Destino> destino = destinoRepository.findById(api.getIdDestino());
        	entity.setDestino(destino.get());
        }
        
        if(tipoAnexo.isPresent()) {
        	entity.setTipoAnexo(tipoAnexo.get());
        }
        
    	DocumentosModelAssembler assembler = new DocumentosModelAssembler(); 
    	DocumentosModel model = assembler.toModel(documentosRepository.save(entity));
 
        
        final URI uri =
                MvcUriComponentsBuilder.fromController(getClass())
                    .path("/{id}")
                    .buildAndExpand(entity.getId())
                    .toUri();
        
        return ResponseEntity.created(uri)
                .body(model);
	}
	
	@PutMapping("/documentos/{id}")
	@ApiOperation(value = "Atualiza um documento")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Documento atualizado"),
    })
	ResponseEntity<DocumentosModel> update(@Valid @RequestBody DocumentosApi api){
		
        Optional<TipoAnexo> tipoAnexo = tipoAnexoRepository.findById(api.getIdTipoAnexo());
		
        Documento entity = api.toEntity();
        
        if(api.getIdControleInterno() != null) {
        	Optional<ControleInterno> controleInterno = controleInternoRepository.findById(api.getIdControleInterno());
        	entity.setControleInterno(controleInterno.get());
        }
        
        if(api.getIdDestino() != null) {
        	Optional<Destino> destino = destinoRepository.findById(api.getIdDestino());
        	entity.setDestino(destino.get());
        }
        
        if(tipoAnexo.isPresent()) {
        	entity.setTipoAnexo(tipoAnexo.get());
        }
		
    	DocumentosModelAssembler assembler = new DocumentosModelAssembler(); 
    	DocumentosModel documentoModel = assembler.toModel(documentosRepository.save(entity));
        
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(documentoModel);

	}
	
	@GetMapping("/documentos")
    @ApiOperation(value = "Retorna uma lista de documentos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Documentos"),
    })
	public ResponseEntity<CollectionModel<DocumentosModel>> getAll() {
    	
    	Collection<Documento> lista = (Collection<Documento>) documentosRepository.findAll();
    	
    	DocumentosModelAssembler assembler = new DocumentosModelAssembler(); 
    	CollectionModel<DocumentosModel> listResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listResource);
	}
    
    @GetMapping("/documentos/{id}")
    @ApiOperation(value = "Retorna um Documentos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma Documentos"),
    })
	public ResponseEntity<DocumentosModel> getById(@PathVariable Long id) {
    	 
    	Optional<Documento> documentos = documentosRepository.findById(id);
    	
    	DocumentosModelAssembler assembler = new DocumentosModelAssembler(); 
    	 
    	return documentos.map(response -> ResponseEntity.ok().body(assembler.toModel(response)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
    
    @DeleteMapping("/documentos/{id}")
    @ApiOperation(value = "Deleta um documento")
    public ResponseEntity<?> delete(@PathVariable Long id) throws NotFoundException{
    	
    	log.info("Removendo documento "+id);
    	
    	 Optional<Documento> documento = documentosRepository.findById(id);
    	 
    	 return documento.map(
    	            p -> {
    	            	documentosRepository.deleteById(id);
    	              return ResponseEntity.noContent().build();
    	            }).orElseThrow(() -> new NotFoundException("Documento não encontrado"));
    	
    }
	
}
