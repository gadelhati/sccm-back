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

import br.com.fattoria.sccm.api.PesquisaCientificaApi;
import br.com.fattoria.sccm.api.PesquisaCientificaModel;
import br.com.fattoria.sccm.api.PesquisaCientificaModelAssembler;
import br.com.fattoria.sccm.persistence.model.Instituicao;
import br.com.fattoria.sccm.persistence.model.PesquisaCientifica;
import br.com.fattoria.sccm.persistence.model.Plataforma;
import br.com.fattoria.sccm.persistence.model.Sigilo;
import br.com.fattoria.sccm.persistence.repository.ComissaoRepository;
import br.com.fattoria.sccm.persistence.repository.InstituicaoRepository;
import br.com.fattoria.sccm.persistence.repository.PesquisaCientificaRepository;
import br.com.fattoria.sccm.persistence.repository.PlataformaRepository;
import br.com.fattoria.sccm.persistence.repository.SigiloRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@Api(value = "Pesquisa Cientifica")
@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class PesquisaCientificaController {

	private final Logger log = LoggerFactory.getLogger(PesquisaCientificaController.class);
	private PesquisaCientificaRepository pesquisaCientificaRepository;
	private PlataformaRepository plataformaRepository;
	private SigiloRepository sigiloRepository;
	private InstituicaoRepository instituicaoRepository;
	private ComissaoRepository comissaoRepository;
	
	public PesquisaCientificaController(PesquisaCientificaRepository pesquisaCientificaRepository, PlataformaRepository plataformaRepository, SigiloRepository sigiloRepository, InstituicaoRepository instituicaoRepository, ComissaoRepository comissaoRepository) {
		this.pesquisaCientificaRepository = pesquisaCientificaRepository;
		this.plataformaRepository = plataformaRepository;
		this.sigiloRepository = sigiloRepository;
		this.instituicaoRepository = instituicaoRepository;
		this.comissaoRepository = comissaoRepository;
	}
	
	@PostMapping("/pesquisas_cientificas")
	@ApiOperation(value = "Cria uma Comissão")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Comissão criada"),
    })
	ResponseEntity<PesquisaCientificaModel> create(@Valid @RequestBody PesquisaCientificaApi api) throws URISyntaxException {
		
		log.info("Salvando "+api);
		
		Optional<Plataforma> plataforma = plataformaRepository.findById(api.getIdPlataforma());
		
		Optional<Instituicao> instituicao = instituicaoRepository.findById(api.getIdInstituicao());
		
		Optional<Sigilo> sigilo = sigiloRepository.findById(api.getIdSigilo());
		
        PesquisaCientifica entity = api.toEntity();
        
        entity.setPlataforma(plataforma.get());
        entity.setInstituicao(instituicao.get());
        entity.setSigilo(sigilo.get());
        
        //entity.setComissao(comissaoRepository.save(entity.getComissao()));
        
    	PesquisaCientificaModelAssembler assembler = new PesquisaCientificaModelAssembler(); 
    	PesquisaCientificaModel model = assembler.toModel(pesquisaCientificaRepository.save(entity));
 
        
        final URI uri =
                MvcUriComponentsBuilder.fromController(getClass())
                    .path("/{id}")
                    .buildAndExpand(entity.getId())
                    .toUri();
        
        return ResponseEntity.created(uri)
                .body(model);
	}
	
	@PutMapping("/pesquisas_cientificas/{id}")
	@ApiOperation(value = "Atualiza uma Pesquisa Cientifica")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Pesquisa Cientifica atualizada"),
    })
	ResponseEntity<PesquisaCientificaModel> update(@Valid @RequestBody PesquisaCientificaApi api){
		
        PesquisaCientifica entity = api.toEntity();
        
    	PesquisaCientificaModelAssembler assembler = new PesquisaCientificaModelAssembler(); 
    	PesquisaCientificaModel model = assembler.toModel(pesquisaCientificaRepository.save(entity));
        
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(model);

	}
	
	@GetMapping("/pesquisas_cientificas")
    @ApiOperation(value = "Retorna uma lista de Pesquisa Cientifica")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Pesquisa Cientifica"),
    })
	public ResponseEntity<CollectionModel<PesquisaCientificaModel>> getAll() {
    	
    	Collection<PesquisaCientifica> lista = (Collection<PesquisaCientifica>) pesquisaCientificaRepository.findAll();
    	
    	PesquisaCientificaModelAssembler assembler = new PesquisaCientificaModelAssembler(); 
    	CollectionModel<PesquisaCientificaModel> listResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listResource);
	}
    
    @GetMapping("/pesquisas_cientificas/{id}")
    @ApiOperation(value = "Retorna uma Pesquisa Cientifica")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma Pesquisa Cientifica"),
    })
	public ResponseEntity<PesquisaCientificaModel> getById(@PathVariable Long id) {
    	 
    	Optional<PesquisaCientifica> entity = pesquisaCientificaRepository.findById(id);
    	
    	PesquisaCientificaModelAssembler assembler = new PesquisaCientificaModelAssembler(); 
    	 
    	return entity.map(response -> ResponseEntity.ok().body(assembler.toModel(response)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
    
    @DeleteMapping("/pesquisas_cientificas/{id}")
    @ApiOperation(value = "Deleta uma Pesquisa Cientifica")
    public ResponseEntity<?> delete(@PathVariable Long id) throws NotFoundException {
    	
    	 pesquisaCientificaRepository.deleteById(id);
    	 Optional<PesquisaCientifica> entity = pesquisaCientificaRepository.findById(id);
    	 
    	 return entity.map(
    	            p -> {
    	            	pesquisaCientificaRepository.deleteById(id);
    	              return ResponseEntity.noContent().build();
    	            }).orElseThrow(() -> new NotFoundException("Pesquisa Cientifica não encontrado"));
    	
    }
	
}
