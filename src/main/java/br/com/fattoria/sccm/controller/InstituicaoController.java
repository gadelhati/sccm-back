package br.com.fattoria.sccm.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.TypedEntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fattoria.sccm.api.InstituicaoApi;
import br.com.fattoria.sccm.api.InstituicaoModel;
import br.com.fattoria.sccm.api.InstituicaoModelAssembler;
import br.com.fattoria.sccm.api.SearchApi;
import br.com.fattoria.sccm.persistence.model.Endereco;
import br.com.fattoria.sccm.persistence.model.Instituicao;
import br.com.fattoria.sccm.persistence.model.Pais;
import br.com.fattoria.sccm.persistence.repository.EnderecoRepository;
import br.com.fattoria.sccm.persistence.repository.InstituicaoRepository;
import br.com.fattoria.sccm.persistence.repository.PaisRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@Api(value = "Instituições")
@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class InstituicaoController {
	
	private final Logger log = LoggerFactory.getLogger(InstituicaoController.class);
	private InstituicaoRepository instituicaoRepository;
	private PaisRepository paisRepository;
	private EnderecoRepository enderecoRepository;
	
	private final TypedEntityLinks<InstituicaoModel> links;
	
	public InstituicaoController(InstituicaoRepository instituicaoRepository, PaisRepository paisRepository, EnderecoRepository enderecoRepository, EntityLinks entityLinks) {
		this.instituicaoRepository = instituicaoRepository;
		this.paisRepository = paisRepository;
		this.enderecoRepository = enderecoRepository;
		this.links = entityLinks.forType(InstituicaoModel::getId);
	}
	
	@PostMapping("/instituicoes")
	@ApiOperation(value = "Cria uma instituicao")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Instituicao criada"),
    })
	ResponseEntity<InstituicaoModel> create(@Valid @RequestBody InstituicaoApi instituicao) throws URISyntaxException{
		
		log.info("criando instituicao");
	
        Optional<Pais> pais = paisRepository.findById(instituicao.getIdPais());
        
        Instituicao instituicaoEntity = instituicao.toEntity();
        
        instituicaoEntity.setPais(pais.get());
        
        if(instituicaoEntity.getEndereco() != null) {
        	instituicaoEntity.setEndereco(enderecoRepository.save(instituicaoEntity.getEndereco()));
        }

    	InstituicaoModelAssembler assembler = new InstituicaoModelAssembler(); 
    	InstituicaoModel instituicaoModel = assembler.toModel(instituicaoRepository.save(instituicaoEntity));
 
        
        final URI uri =
                MvcUriComponentsBuilder.fromController(getClass())
                    .path("/{id}")
                    .buildAndExpand(instituicaoEntity.getId())
                    .toUri();
        
        return ResponseEntity.created(uri)
                .body(instituicaoModel);
	}
	
	@PutMapping("/instituicoes/{id}")
	@ApiOperation(value = "Atualiza uma instituicao")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Instituicao atualizada"),
    })
	ResponseEntity<InstituicaoModel> update(@Valid @RequestBody InstituicaoApi instituicao){
		
		log.info("alterando instituicao");
		
        Optional<Pais> pais = paisRepository.findById(instituicao.getIdPais());
        
        Instituicao instituicaoEntity = instituicao.toEntity();
        
        instituicaoEntity.setPais(pais.get());
        
        if(instituicaoEntity.getEndereco() != null) {
        	Endereco endereco = instituicaoEntity.getEndereco();
        	Endereco enderecoByIdInstituicao = enderecoRepository.getEnderecoByIdInstituicao(instituicaoEntity.getId());
        	endereco.setId(enderecoByIdInstituicao != null ? enderecoByIdInstituicao.getId() : null);
        	instituicaoEntity.setEndereco(enderecoRepository.save(endereco));
        }
        
    	InstituicaoModelAssembler assembler = new InstituicaoModelAssembler(); 
    	InstituicaoModel instituicaoModel = assembler.toModel(instituicaoRepository.save(instituicaoEntity));
        
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(instituicaoModel);

	}
	
	@GetMapping("/instituicoes/pagina")
    @ApiOperation(value = "Retorna uma lista de instituicoes")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de instituicoes"),
    })
	public ResponseEntity<Page<InstituicaoModel>> getAll(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam(required = false) String search) {
		
		log.info("ObjectUtils.isEmpty(search) "+ObjectUtils.isEmpty(search));
		log.info("paginando instituicoes "+search);
		
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by("nome").descending());
    	
    	Page<Instituicao> lista = null;
    	
    	lista = ObjectUtils.isEmpty(search) ? instituicaoRepository.findAll(pageRequest) : 
    		instituicaoRepository.findAllBySearch(pageRequest, "%"+search+"%");
    	
    	InstituicaoModelAssembler assembler = new InstituicaoModelAssembler(); 
    	CollectionModel<InstituicaoModel> listResource = assembler.toCollectionModel(lista.toList());
    	
    	Page<InstituicaoModel> pageFull = new PageImpl<InstituicaoModel>(new ArrayList<InstituicaoModel>(listResource.getContent()), lista.getPageable(), lista.getTotalElements());
    	
	    return ResponseEntity.ok(pageFull);
	}
	
	@GetMapping("/instituicoes")
    @ApiOperation(value = "Retorna uma lista de instituicoes")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de instituicoes"),
    })
	public ResponseEntity<CollectionModel<InstituicaoModel>> getAll() {
    	
    	log.info("listando instituicao");
    	 
    	Collection<Instituicao> lista = (Collection<Instituicao>) instituicaoRepository.findAll();
    	
    	InstituicaoModelAssembler assembler = new InstituicaoModelAssembler(); 
    	CollectionModel<InstituicaoModel> listInstituicaoResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listInstituicaoResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listInstituicaoResource);
	}
    
    @GetMapping("/instituicoes/{id}")
    @ApiOperation(value = "Retorna uma instituicao")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma instituicao"),
    })
	public ResponseEntity<InstituicaoModel> getById(@PathVariable Long id) {
    	
    	log.info("instituicao por id "+id);
    	 
    	Optional<Instituicao> instituicao = instituicaoRepository.findById(id);
    	
    	InstituicaoModelAssembler assembler = new InstituicaoModelAssembler(); 
    	 
    	return instituicao.map(response -> ResponseEntity.ok().body(assembler.toModel(response)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
    
    @DeleteMapping("/instituicoes/{id}")
    @ApiOperation(value = "Deleta uma instituicao")
    public ResponseEntity<?> delete(@PathVariable Long id) throws NotFoundException {
    	
    	 log.info("instituicao por id "+id);
    	
    	 instituicaoRepository.deleteById(id);
    	 Optional<Instituicao> instituicao = instituicaoRepository.findById(id);
    	 
    	 return instituicao.map(
    	            p -> {
    	            	instituicaoRepository.deleteById(id);
    	              return ResponseEntity.noContent().build();
    	            }).orElseThrow(() -> new NotFoundException("Instituicao não encontrada"));
    	
    }
    
	@PutMapping("/instituicoes/active/{id}")
	@ApiOperation(value = "Atualiza uma instituicao")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Instituicao atualizada"),
    })
	ResponseEntity<InstituicaoModel> active(@Valid @PathVariable Long id){
		
		log.info("alterando instituicao");
		
		Optional<Instituicao> instituicao = instituicaoRepository.findById(id);
		
		InstituicaoModelAssembler assembler = new InstituicaoModelAssembler(); 
		
    	return instituicao.map(response -> {
    		response.setAtivo(!response.isAtivo());
    		instituicaoRepository.save(response);
    		return ResponseEntity.ok().body(assembler.toModel(response));
    	}).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

	}
	
	@GetMapping("/instituicoes/ativas")
    @ApiOperation(value = "Retorna uma lista de instituicoes ativas")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de instituicoes ativas"),
    })
	public ResponseEntity<CollectionModel<InstituicaoModel>> getAllAtivas() {
    	
    	log.info("listando instituicao");
    	 
    	Collection<Instituicao> lista = (Collection<Instituicao>) instituicaoRepository.findAllByAtivoTrue();
    	
    	InstituicaoModelAssembler assembler = new InstituicaoModelAssembler(); 
    	CollectionModel<InstituicaoModel> listInstituicaoResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listInstituicaoResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listInstituicaoResource);
	}
	
	@GetMapping("/instituicoes/privada/ativas")
    @ApiOperation(value = "Retorna uma lista de instituicoes ativas")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de instituicoes privadas ativas"),
    })
	public ResponseEntity<CollectionModel<InstituicaoModel>> getAllPrivadasAtivas() {
    	
    	log.info("listando instituicao");
    	 
    	Collection<Instituicao> lista = (Collection<Instituicao>) instituicaoRepository.findAllByTipoInstituicaoPrivadoAndAtivoTrue();
    	
    	InstituicaoModelAssembler assembler = new InstituicaoModelAssembler(); 
    	CollectionModel<InstituicaoModel> listInstituicaoResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listInstituicaoResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listInstituicaoResource);
	}
	
	@GetMapping("/instituicoes/publica/ativas")
    @ApiOperation(value = "Retorna uma lista de instituicoes ativas")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de instituicoes publicas ativas"),
    })
	public ResponseEntity<CollectionModel<InstituicaoModel>> getAllPublicasAtivas() {
    	
    	log.info("listando instituicao");
    	 
    	Collection<Instituicao> lista = (Collection<Instituicao>) instituicaoRepository.findAllByTipoInstituicaoPublicoAndAtivoTrue();
    	
    	InstituicaoModelAssembler assembler = new InstituicaoModelAssembler(); 
    	CollectionModel<InstituicaoModel> listInstituicaoResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listInstituicaoResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listInstituicaoResource);
	}
	
	@PostMapping("/instituicoes/ativas/searchNomeSigla")
    @ApiOperation(value = "Retorna uma lista de instituicoes ativas")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de instituicoes ativas"),
    })
	public ResponseEntity<CollectionModel<InstituicaoModel>> getAllAtivasByNomeOrSigla(@RequestBody SearchApi searchApi) {
    	
    	log.info("listando instituicao");
    	 
    	Collection<Instituicao> lista = (Collection<Instituicao>) instituicaoRepository.findAllByAtivoTrueAndNomeContainingIgnoreCaseOrSiglaContainingIgnoreCase(searchApi.getSearch() != null ? "%"+searchApi.getSearch()+"%" : "");
    	
    	InstituicaoModelAssembler assembler = new InstituicaoModelAssembler(); 
    	CollectionModel<InstituicaoModel> listInstituicaoResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listInstituicaoResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listInstituicaoResource);
	}
	
	@PostMapping("/instituicoes/privada/ativas/searchNomeSigla")
    @ApiOperation(value = "Retorna uma lista de instituicoes ativas")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de instituicoes privadas ativas"),
    })
	public ResponseEntity<CollectionModel<InstituicaoModel>> getAllPrivadasAtivasByNomeOrSigla(@RequestBody SearchApi searchApi) {
    	
    	log.info("listando instituicao");
    	 
    	Collection<Instituicao> lista = (Collection<Instituicao>) instituicaoRepository.findAllByTipoInstituicaoPrivadoAndAtivoTrueAndNomeContainingIgnoreCaseOrSiglaContainingIgnoreCase(searchApi.getSearch() != null ? "%"+searchApi.getSearch()+"%" : "");
    	
    	InstituicaoModelAssembler assembler = new InstituicaoModelAssembler(); 
    	CollectionModel<InstituicaoModel> listInstituicaoResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listInstituicaoResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listInstituicaoResource);
	}
	
	@PostMapping("/instituicoes/publica/ativas/searchNomeSigla")
    @ApiOperation(value = "Retorna uma lista de instituicoes ativas")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de instituicoes publicas ativas"),
    })
	public ResponseEntity<CollectionModel<InstituicaoModel>> getAllPublicasAtivasByNomeOrSigla(@RequestBody SearchApi searchApi) {
    	
    	log.info("listando instituicao");
    	 
    	Collection<Instituicao> lista = (Collection<Instituicao>) instituicaoRepository.findAllByTipoInstituicaoPublicoAndAtivoTrueAndNomeContainingIgnoreCaseOrSiglaContainingIgnoreCase(searchApi.getSearch() != null ? "%"+searchApi.getSearch()+"%" : "");
    	
    	InstituicaoModelAssembler assembler = new InstituicaoModelAssembler(); 
    	CollectionModel<InstituicaoModel> listInstituicaoResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listInstituicaoResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listInstituicaoResource);
	}

}
