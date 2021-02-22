package br.com.fattoria.sccm.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fattoria.sccm.api.PesquisaCientificaDadosApi;
import br.com.fattoria.sccm.api.PesquisaCientificaDadosModel;
import br.com.fattoria.sccm.api.PesquisaCientificaDadosModelAssembler;
import br.com.fattoria.sccm.persistence.model.PesquisaCientificaDados;
import br.com.fattoria.sccm.persistence.model.PesquisaCientificaDadosPk;
import br.com.fattoria.sccm.persistence.model.TipoDado;
import br.com.fattoria.sccm.persistence.repository.PesquisaCientificaDadosRepository;
import br.com.fattoria.sccm.persistence.repository.PesquisaCientificaRepository;
import br.com.fattoria.sccm.persistence.repository.TipoDadoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Dados de Coleta de Pesquisa Cientifica")
@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class PesquisaCientificaDadosController {

	private final Logger log = LoggerFactory.getLogger(PesquisaCientificaDadosController.class);
	private final PesquisaCientificaRepository pesquisaCientificaRepository;
	private final PesquisaCientificaDadosRepository pesquisaCientificaDadosRepository;
	private final TipoDadoRepository tipoDadoRepository;
	
	public PesquisaCientificaDadosController(PesquisaCientificaRepository pesquisaCientificaRepository,
			PesquisaCientificaDadosRepository pesquisaCientificaDadosRepository,
			TipoDadoRepository tipoDadoRepository) {
		super();
		this.pesquisaCientificaRepository = pesquisaCientificaRepository;
		this.pesquisaCientificaDadosRepository = pesquisaCientificaDadosRepository;
		this.tipoDadoRepository = tipoDadoRepository;
	}

	@PostMapping("/pesquisa_cientifica_dados")
	@ApiOperation(value = "Cria Dados de Coleta")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Dados de Coleta adicionados"),
    })
	ResponseEntity<CollectionModel<PesquisaCientificaDadosModel>> create(@Valid @RequestBody PesquisaCientificaDadosApi api) throws URISyntaxException {
		
		log.info("Salvando "+api);
		
		Collection<PesquisaCientificaDados> dadosByPesquisaCientifica = pesquisaCientificaDadosRepository.findAllByIdPesquisaCientifica(api.getIdPesquisaCientifica());
		pesquisaCientificaDadosRepository.deleteAll(dadosByPesquisaCientifica);
        
		List<PesquisaCientificaDados> listaPCD = new ArrayList<PesquisaCientificaDados>();
        if(api.getIdsDadosEquipamentos() != null && api.getIdsDadosEquipamentos().size() > 0) {
	        Iterable<TipoDado> dadosEquipamentos = tipoDadoRepository.findAllById(api.getIdsDadosEquipamentos());
	        
	        for (TipoDado tipoDado : dadosEquipamentos) {
	        	PesquisaCientificaDados pesquisaCientificaDados = new PesquisaCientificaDados(new PesquisaCientificaDadosPk(api.getIdPesquisaCientifica(), tipoDado.getId()));
	        	listaPCD.add(pesquisaCientificaDadosRepository.save(pesquisaCientificaDados));
			}
        }
        
        PesquisaCientificaDadosModelAssembler assembler = new PesquisaCientificaDadosModelAssembler(); 
    	CollectionModel<PesquisaCientificaDadosModel> model = assembler.toCollectionModel(listaPCD);
 
        
        final URI uri =
                MvcUriComponentsBuilder.fromController(getClass())
                    .path("/{id}")
                    .buildAndExpand(api.getIdPesquisaCientifica())
                    .toUri();
        
        return ResponseEntity.created(uri)
                .body(model);
	}
	
	@PutMapping("/pesquisa_cientifica_dados/{id}")
	@ApiOperation(value = "Atualiza dados de Coleta")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Dados de Coleta atualizados"),
    })
	ResponseEntity<CollectionModel<PesquisaCientificaDadosModel>> update(@Valid @RequestBody PesquisaCientificaDadosApi api){
		
		log.info("Alterando "+api);
		
		Collection<PesquisaCientificaDados> dadosByPesquisaCientifica = pesquisaCientificaDadosRepository.findAllByIdPesquisaCientifica(api.getIdPesquisaCientifica());
		pesquisaCientificaDadosRepository.deleteAll(dadosByPesquisaCientifica);
		
		List<PesquisaCientificaDados> listaPCD = new ArrayList<PesquisaCientificaDados>();
        if(api.getIdsDadosEquipamentos() != null && api.getIdsDadosEquipamentos().size() > 0) {
	        Iterable<TipoDado> dadosEquipamentos = tipoDadoRepository.findAllById(api.getIdsDadosEquipamentos());
	        
	        for (TipoDado tipoDado : dadosEquipamentos) {
	        	PesquisaCientificaDados pesquisaCientificaDados = new PesquisaCientificaDados(new PesquisaCientificaDadosPk(api.getIdPesquisaCientifica(), tipoDado.getId()));
	        	listaPCD.add(pesquisaCientificaDadosRepository.save(pesquisaCientificaDados));
			}
        }
        
        PesquisaCientificaDadosModelAssembler assembler = new PesquisaCientificaDadosModelAssembler(); 
    	CollectionModel<PesquisaCientificaDadosModel> model = assembler.toCollectionModel(listaPCD);
        
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(model);

	}
	
    @GetMapping("/pesquisa_cientifica_dados/{id}")
    @ApiOperation(value = "Retorna dados de Coleta")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna dados de Coleta"),
    })
	public ResponseEntity<CollectionModel<PesquisaCientificaDadosModel>> getByIdPesquisaCientifica(@PathVariable Long id) {
    	 
    	Collection<PesquisaCientificaDados> lista = (Collection<PesquisaCientificaDados>) pesquisaCientificaDadosRepository.findAllByIdPesquisaCientifica(id);
    	
    	PesquisaCientificaDadosModelAssembler assembler = new PesquisaCientificaDadosModelAssembler(); 
    	CollectionModel<PesquisaCientificaDadosModel> listResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listResource);
	}


	
}
