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

import br.com.fattoria.sccm.api.ControleInternoApi;
import br.com.fattoria.sccm.api.ControleInternoModel;
import br.com.fattoria.sccm.api.ControleInternoModelAssembler;
import br.com.fattoria.sccm.api.DocumentosModel;
import br.com.fattoria.sccm.api.DocumentosModelAssembler;
import br.com.fattoria.sccm.api.ShipSynopModel;
import br.com.fattoria.sccm.persistence.model.ControleInterno;
import br.com.fattoria.sccm.persistence.model.Documento;
import br.com.fattoria.sccm.persistence.model.Instituicao;
import br.com.fattoria.sccm.persistence.model.PesquisaCientifica;
import br.com.fattoria.sccm.persistence.repository.ControleInternoRepository;
import br.com.fattoria.sccm.persistence.repository.DocumentosRepository;
import br.com.fattoria.sccm.persistence.repository.InstituicaoRepository;
import br.com.fattoria.sccm.persistence.repository.PesquisaCientificaRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@Api(value = "controleInterno")
@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class ControleInternoController {

	private final Logger log = LoggerFactory.getLogger(ControleInternoController.class);
	private ControleInternoRepository controleInternoRepository;
	private InstituicaoRepository instituicaoRepository;
	private PesquisaCientificaRepository pesquisaCientificaRepository;
	private DocumentosRepository documentosRepository;
	
	private final TypedEntityLinks<ShipSynopModel> links;
	
	public ControleInternoController(ControleInternoRepository controleInternoRepository, 
			InstituicaoRepository instituicaoRepository, PesquisaCientificaRepository pesquisaCientificaRepository, DocumentosRepository documentosRepository, EntityLinks entityLinks) {
		this.controleInternoRepository = controleInternoRepository;
		this.instituicaoRepository = instituicaoRepository;
		this.pesquisaCientificaRepository = pesquisaCientificaRepository;
		this.documentosRepository = documentosRepository;
		this.links = entityLinks.forType(ShipSynopModel::getId);
	}
	
	@PostMapping("/controleInterno")
	@ApiOperation(value = "Cria um Destino")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Destino criado"),
    })
	ResponseEntity<ControleInternoModel> create(@Valid @RequestBody ControleInternoApi api) throws URISyntaxException {
		
		Optional<Instituicao> instituicao = instituicaoRepository.findById(api.getIdInstituicao());
		
		Optional<PesquisaCientifica> pesquisaCientifica = pesquisaCientificaRepository.findById(api.getIdPesquisaCientifica());
		
        ControleInterno entity = api.toEntity();
        
        entity.setInstituicao(instituicao.get());
        entity.setPesquisaCientifica(pesquisaCientifica.get());
        
    	ControleInternoModelAssembler assembler = new ControleInternoModelAssembler(); 
    	ControleInternoModel model = assembler.toModel(controleInternoRepository.save(entity));
 
        
        final URI uri =
                MvcUriComponentsBuilder.fromController(getClass())
                    .path("/{id}")
                    .buildAndExpand(entity.getId())
                    .toUri();
        
        return ResponseEntity.created(uri)
                .body(model);
	}
	
	@PutMapping("/controleInterno/{id}")
	@ApiOperation(value = "Atualizar")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Atualizado com Sucesso!"),
    })
	ResponseEntity<ControleInternoModel> update(@Valid @RequestBody ControleInternoApi api){
		
		Optional<Instituicao> instituicao = instituicaoRepository.findById(api.getIdInstituicao());
		
		Optional<PesquisaCientifica> pesquisaCientifica = pesquisaCientificaRepository.findById(api.getIdPesquisaCientifica());
		
		ControleInterno entity = api.toEntity();
        
		entity.setInstituicao(instituicao.get());
        entity.setPesquisaCientifica(pesquisaCientifica.get());
		
    	ControleInternoModelAssembler assembler = new ControleInternoModelAssembler(); 
    	ControleInternoModel model = assembler.toModel(controleInternoRepository.save(entity));
        
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(model);

	}
	
	@GetMapping("/controleInterno")
    @ApiOperation(value = "Todos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Sucesso"),
    })
	public ResponseEntity<CollectionModel<ControleInternoModel>> getAll() {
    	
    	Collection<ControleInterno> lista = (Collection<ControleInterno>) controleInternoRepository.findAll();
    	
    	ControleInternoModelAssembler assembler = new ControleInternoModelAssembler(); 
    	CollectionModel<ControleInternoModel> listPlataformaResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listPlataformaResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listPlataformaResource);
	}
    
    @GetMapping("/controleInterno/{id}")
    @ApiOperation(value = "Retorna um objeto")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornou um objeto"),
    })
	public ResponseEntity<ControleInternoModel> getById(@PathVariable Long id) {
    	 
    	Optional<ControleInterno> object = controleInternoRepository.findById(id);
    	
    	ControleInternoModelAssembler assembler = new ControleInternoModelAssembler(); 
    	 
    	return object.map(response -> ResponseEntity.ok().body(assembler.toModel(response)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
    
    @DeleteMapping("/controleInterno/{id}")
    @ApiOperation(value = "Deleta")
    public ResponseEntity<?> delete(@PathVariable Long id) throws NotFoundException {
    	
    	 Optional<ControleInterno> object = controleInternoRepository.findById(id);
    	 
    	 return object.map(
    	            p -> {
    	            	controleInternoRepository.deleteById(id);
    	              return ResponseEntity.noContent().build();
    	            }).orElseThrow(() -> new NotFoundException("Não encontrado"));
    	
    }
    
	
	@GetMapping("/controleInterno/{id}/documentos")
    @ApiOperation(value = "Retorna uma lista de documentos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma lista de documentos"),
    })
	public ResponseEntity<CollectionModel<DocumentosModel>> getAllDocumentosByIdPesquisaCientifica(@PathVariable Long id) {
    	
    	log.info("listando documentos");
    	 
    	Collection<Documento> lista = (Collection<Documento>) documentosRepository.findAllByControleInternoId(id);
    	
    	DocumentosModelAssembler assembler = new DocumentosModelAssembler(); 
    	CollectionModel<DocumentosModel> listResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listResource);
	}
}
